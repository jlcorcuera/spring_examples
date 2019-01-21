/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.idc.rest.test.swagger2.dto.SalesOrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.idc.rest.test.swagger2.service.SalesOrderService;
import pe.idc.rest.test.swagger2.web.rest.base.ApiResult;
/**
 *
 * @author j0s3
 */
@RestController
@RequestMapping(value = "/salesOrder")
@Api("Sales Order EndPoint")
public class SalesOrderRest {

    private static final Logger log = LoggerFactory.getLogger(SalesOrderRest.class);
    
    @Autowired
    private SalesOrderService salesOrderService;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json") 
    @ApiOperation("Return all the sales order")    
    @ResponseBody
    public List<SalesOrderDTO> list() {
        log.info("Sending message to log info from list");
        List<SalesOrderDTO> salesOrders = salesOrderService.list();
        return salesOrders;
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json") 
    @ApiOperation("Update an existing sales order")    
    @ResponseBody
    public ApiResult edit(@ApiParam(required = true, name = "salesOrder", 
            value = "Sales Order to update") @RequestBody SalesOrderDTO salesOrder) {
        log.info("On edit");
        salesOrderService.update(salesOrder);
        salesOrder = salesOrderService.loadById(salesOrder.getId());
        return new ApiResult(true, "Sales order updated successfully", salesOrder);
    }    

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json") 
    @ApiOperation("Stores a new sales order")    
    @ResponseBody
    public ApiResult save(@ApiParam(required = true, name = "salesOrder", 
            value = "Sales Order to store") @RequestBody SalesOrderDTO salesOrder) {
        log.info("On save " + salesOrder);
        salesOrder = salesOrderService.save(salesOrder);
        return new ApiResult(true, "Sales order stored successfully", salesOrder);
    }
    
    @DeleteMapping(value = "/delete/{id}", produces = "application/json") 
    @ApiOperation("Delete a sales order by Id.")    
    @ResponseBody
    public ApiResult delete(@ApiParam(required = true, name = "id", 
            value = "Sales Order Id", defaultValue = "0") @PathVariable Integer id) {
        log.info("On delete " + id);
        salesOrderService.delete(id);
        return new ApiResult(true, "Sales order deleted successfully");
    }    
}
