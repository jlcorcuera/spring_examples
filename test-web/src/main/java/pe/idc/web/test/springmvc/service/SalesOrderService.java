/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.idc.web.test.springmvc.dto.SalesOrderDTO;
import pe.idc.web.test.springmvc.dto.SalesOrderDetailDTO;
import pe.idc.web.test.springmvc.model.Customer;
import pe.idc.web.test.springmvc.model.SalesOrder;
import pe.idc.web.test.springmvc.model.SalesOrderDetail;
import pe.idc.web.test.springmvc.repository.CustomerRepository;
import pe.idc.web.test.springmvc.repository.SalesOrderRepository;
import pe.idc.web.test.springmvc.service.exception.ServiceException;

/**
 *
 * @author j0s3
 */
@Service
@Transactional(readOnly = true)
public class SalesOrderService {

    @Autowired
    @Qualifier("customerDBImpl")
    private CustomerRepository customerRepository;    
    
    @Autowired
    @Qualifier("salesOrderDBImpl")
    private SalesOrderRepository salesOrderRepository;
    
    @Transactional
    public SalesOrderDTO save(SalesOrderDTO salesOrderDTO){
        Integer customerId = salesOrderDTO.getCustomerId();
        String salesman = salesOrderDTO.getSalesman();
        List<String> errors = new ArrayList<>();
        if (customerId == null || customerId <= 0){
            errors.add("CustomerId is required.");
        }
        if (salesman == null || salesman.isEmpty()){
            errors.add("Salesman is required.");
        }
        //there may be more validations here
        //for example verify that the sales order has sales order details
        if (!errors.isEmpty()){
            throw new ServiceException("Validation error", errors);
        }
        Customer customer = customerRepository.loadById(customerId);
        //ensure that customer exists (it is not null)
        //note that we are not setting the id attribute since we want to persist the object
        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomer(customer);
        salesOrder.setOrderDate(salesOrderDTO.getDate());
        salesOrder.setTaxes(salesOrderDTO.getTaxes());
        salesOrder.setTotal(salesOrderDTO.getTotal());
        salesOrder.setDetails(new ArrayList<>());
        
        for(SalesOrderDetailDTO salesOrderDetailDTO:salesOrderDTO.getDetails()){
            SalesOrderDetail salesOrderDetail = new SalesOrderDetail();
            salesOrderDetail.setProduct(salesOrderDetailDTO.getProduct());
            salesOrderDetail.setQuantity(salesOrderDetailDTO.getQuantity());
            salesOrderDetail.setSubTotal(salesOrderDetailDTO.getSubtotal());
            //setting its parent to the detail
            salesOrderDetail.setSalesOrder(salesOrder);
            //adding the detail to the order
            salesOrder.getDetails().add(salesOrderDetail);
        }
        salesOrder = salesOrderRepository.save(salesOrder);
        
        //lets suppose that the numDoc attribute value is the generated id
        salesOrderDTO.setId(salesOrder.getId());
        return salesOrderDTO;
    }
    
    @Transactional
    public SalesOrderDTO update(SalesOrderDTO salesOrderDTO){
        Integer customerId = salesOrderDTO.getCustomerId();
        String salesman = salesOrderDTO.getSalesman();
        Integer salesOrderId = salesOrderDTO.getId();
        List<String> errors = new ArrayList<>();
        if (salesOrderId == null || salesOrderId <= 0){
            errors.add("SalesOrderId is required.");
        }        
        if (customerId == null || customerId <= 0){
            errors.add("CustomerId is required.");
        }
        if (salesman == null || salesman.isEmpty()){
            errors.add("Salesman is required.");
        }
        //there may be more validations here
        //for example verify that the sales order has sales order details
        if (!errors.isEmpty()){
            throw new ServiceException("Validation error", errors);
        }
        Customer customer = customerRepository.loadById(customerId);
        //we just basically load the existing object and proceed to update it
        SalesOrder salesOrder = salesOrderRepository.loadById(salesOrderId);
        //by doing .getDetails we fetch all the details related to the sales order
        //salesOrderDetails points to the details list!
        List<SalesOrderDetail> salesOrderDetails = salesOrder.getDetails();
        Map<Integer, SalesOrderDetail> mapSOD = new HashMap<>();
        for(SalesOrderDetail sod:salesOrderDetails){
            mapSOD.put(sod.getId(), sod);
        }
        //ensure that customer exists (it is not null)
        //note that we are not setting the id attribute since we want to persist the object
        salesOrder.setCustomer(customer);
        salesOrder.setOrderDate(salesOrderDTO.getDate());
        salesOrder.setTaxes(salesOrderDTO.getTaxes());
        salesOrder.setTotal(salesOrderDTO.getTotal());
        
        for(SalesOrderDetailDTO salesOrderDetailDTO:salesOrderDTO.getDetails()){
            Integer sodId = salesOrderDetailDTO.getId();
            SalesOrderDetail salesOrderDetail = null;
            //if sodId is null means that you want to insert a new detail
            if (sodId == null || sodId <= 0){
                salesOrderDetail = new SalesOrderDetail();
                //adding a new detail to the order
                salesOrderDetails.add(salesOrderDetail);                
            }else{
                //otherwise, you are trying to modify an existing one
                //note that the remaining objects in the map structure are those details
                //which need to be deleted since they haven't been sent again in the update
                salesOrderDetail = mapSOD.remove(sodId);
                /* at this point, it may be that salesOrderDetail is null (another transaction
                    could have been deleted this sales order detail
                   think about how this logic may overcome or behave when this happen
                */
            }
            salesOrderDetail.setProduct(salesOrderDetailDTO.getProduct());
            salesOrderDetail.setQuantity(salesOrderDetailDTO.getQuantity());
            salesOrderDetail.setSubTotal(salesOrderDetailDTO.getSubtotal());
            //setting its parent to the detail
            salesOrderDetail.setSalesOrder(salesOrder);
        }
        
        //removing remaining details
        for(SalesOrderDetail salesOrderDetail:mapSOD.values()){
            salesOrderDetails.remove(salesOrderDetail);
        }
        
        salesOrder = salesOrderRepository.save(salesOrder);
        //lets suppose that the numDoc attribute value is the generated id
        salesOrderDTO.setNumDoc(salesOrder.getId() + "");
        return salesOrderDTO;
    }    
    
    public SalesOrderDTO loadById(Integer salesOrderId){
        SalesOrder salesOrder = salesOrderRepository.loadById(salesOrderId);
        List<SalesOrderDetail> salesOrderDetails = salesOrder.getDetails();
        
        SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
        List<SalesOrderDetailDTO> salesOrderDetailsDTO = new ArrayList<>();
        salesOrderDTO.setDetails(salesOrderDetailsDTO);
        
        salesOrderDTO.setCustomer(salesOrder.getCustomer().getName());
        salesOrderDTO.setDate(salesOrder.getOrderDate());
        salesOrderDTO.setId(salesOrder.getId());
        salesOrderDTO.setTotal(salesOrder.getTotal());
        salesOrderDTO.setTaxes(salesOrder.getTaxes());
        
        for(SalesOrderDetail salesOrderDetail:salesOrderDetails){
            SalesOrderDetailDTO item = new SalesOrderDetailDTO();
            item.setId(salesOrderDetail.getId());
            item.setProduct(salesOrderDetail.getProduct());
            item.setQuantity(salesOrderDetail.getQuantity());
            item.setSubtotal(salesOrderDetail.getSubTotal());
            salesOrderDetailsDTO.add(item);
        }
        return salesOrderDTO;
    }
    
    @Transactional
    public void delete(Integer salesOrderId){
        salesOrderRepository.delete(salesOrderId);
    }
    
    public List<SalesOrderDTO> list(){
        return salesOrderRepository.list();
    }
}
