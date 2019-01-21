/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.repository;

import pe.idc.web.test.springmvc.model.Customer;

/**
 *
 * @author j0s3
 */
public interface CustomerRepository {
    
    public Customer loadById(Integer id);
    
}
