/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.repository;

import java.util.List;
import pe.idc.web.test.springmvc.model.SalesOrder;
import pe.idc.web.test.springmvc.dto.SalesOrderDTO;

/**
 *
 * @author j0s3
 */
public interface SalesOrderRepository {
    
    public SalesOrder save(SalesOrder salesOrder);
    public SalesOrder loadById(Integer id);
    public List<SalesOrderDTO> list();
    public void delete(Integer id);
    
}
