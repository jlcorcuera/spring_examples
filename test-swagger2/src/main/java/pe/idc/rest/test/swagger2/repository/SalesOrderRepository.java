/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.repository;

import java.util.List;
import pe.idc.rest.test.swagger2.dto.SalesOrderDTO;
import pe.idc.rest.test.swagger2.model.SalesOrder;

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
