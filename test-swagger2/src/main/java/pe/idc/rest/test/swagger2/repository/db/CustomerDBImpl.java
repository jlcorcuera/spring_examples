/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.repository.db;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pe.idc.rest.test.swagger2.model.Customer;
import pe.idc.rest.test.swagger2.repository.CustomerRepository;

/**
 *
 * @author j0s3
 */
@Repository("customerDBImpl")
public class CustomerDBImpl implements CustomerRepository{
    
    @PersistenceContext
    private EntityManager entityManager;    

    @Override
    public Customer loadById(Integer id) {
        return entityManager.find(Customer.class, id);
    }

}
