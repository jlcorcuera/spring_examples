/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pe.idc.web.test.springmvc.service.SalesOrderService;
import pe.idc.web.test.springmvc.dto.SalesOrderDTO;
import pe.idc.web.test.springmvc.controller.base.ApiResult;
/**
 *
 * @author j0s3
 */
@Controller
@RequestMapping(value = "/salesOrder")
public class SalesOrderController {

    private static final Logger log = LoggerFactory.getLogger(SalesOrderController.class);
    
    @Autowired
    private SalesOrderService salesOrderService;
    
    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = "application/json") 
    @ResponseBody
    public List<SalesOrderDTO> list(@RequestParam(name = "query", required = false) String query) {
        log.info("Sending message to log info from list, query: " + query);
        List<SalesOrderDTO> salesOrders = salesOrderService.list();
        return salesOrders;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json") 
    @ResponseBody
    public ApiResult edit(@RequestBody SalesOrderDTO salesOrder) {
        log.info("On edit");
        salesOrderService.update(salesOrder);
        salesOrder = salesOrderService.loadById(salesOrder.getId());
        return new ApiResult(true, "Sales order updated successfully", salesOrder);
    }    

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")    
    @ResponseBody
    public ApiResult save(@RequestBody SalesOrderDTO salesOrder) {
        log.info("On save " + salesOrder);
        salesOrder = salesOrderService.save(salesOrder);
        return new ApiResult(true, "Sales order stored successfully", salesOrder);
    }
    
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")  
    @ResponseBody
    public ApiResult delete(@PathVariable Integer id) {
        log.info("On delete " + id);
        salesOrderService.delete(id);
        return new ApiResult(true, "Sales order deleted successfully");
    }    
}
