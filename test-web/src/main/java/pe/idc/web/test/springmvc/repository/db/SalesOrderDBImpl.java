/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.repository.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import pe.idc.web.test.springmvc.dto.SalesOrderDTO;
import pe.idc.web.test.springmvc.model.SalesOrder;
import pe.idc.web.test.springmvc.repository.SalesOrderRepository;
import pe.idc.web.test.springmvc.service.exception.ServiceException;

/**
 *
 * @author j0s3
 */
@Repository("salesOrderDBImpl")
public class SalesOrderDBImpl implements SalesOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SalesOrder save(SalesOrder salesOrder) {
        boolean toPersist = salesOrder.getId() == null;
        if (toPersist) {
            entityManager.persist(salesOrder);
            entityManager.refresh(salesOrder);
        }
        return salesOrder;
    }

    @Override
    public SalesOrder loadById(Integer id) {
        return entityManager.find(SalesOrder.class, id);
    }

    @Override
    public List<SalesOrderDTO> list() {
        StringBuilder sb = new StringBuilder();
        sb.append("select ");
        sb.append("so.id, ");//0
        sb.append("so.order_date, ");//1
        sb.append("c.name, ");//2
        sb.append("so.taxes, ");//3
        sb.append("so.total ");//4
        sb.append("from ");
        sb.append("sales_order so ");
        sb.append("inner join customer c on c.id = so.customer_id ");
        sb.append("order by  ");
        sb.append("so.order_date desc ");
        Query query = entityManager.createNativeQuery(sb.toString());
        List<Object[]> results = query.getResultList();
        if (results != null && !results.isEmpty()) {
            List<SalesOrderDTO> salesOrderDTOs = new ArrayList<SalesOrderDTO>();
            for (Object[] obj : results) {
                SalesOrderDTO salesOrderDTO = new SalesOrderDTO();
                salesOrderDTO.setId(Integer.parseInt(obj[0].toString()));
                salesOrderDTO.setDate((Date)(obj[1]));
                salesOrderDTO.setCustomer(obj[2].toString());
                if (obj[3] != null){
                    salesOrderDTO.setTaxes(Double.parseDouble(obj[3].toString()));
                }
                if (obj[4] != null){
                    salesOrderDTO.setTotal(Double.parseDouble(obj[4].toString()));
                }
                salesOrderDTOs.add(salesOrderDTO);
            }
            return salesOrderDTOs;
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        int isSuccessful = entityManager.createQuery("delete from SalesOrderDetail sod where sod.salesOrder.id = :id")
                .setParameter("id", id).executeUpdate();

        isSuccessful = entityManager.createQuery("delete from SalesOrder so where so.id = :id")
                .setParameter("id", id).executeUpdate();

        if (isSuccessful == 0) {
            throw new ServiceException("No SalesOrder found with id " + id);
        }
    }

}
