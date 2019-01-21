/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.repository;

import pe.idc.rest.test.swagger2.model.Customer;

/**
 *
 * @author j0s3
 */
public interface CustomerRepository {
    
    public Customer loadById(Integer id);
    
}
