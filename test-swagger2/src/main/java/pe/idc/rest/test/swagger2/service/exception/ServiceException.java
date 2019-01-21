/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.service.exception;

import java.util.List;

/**
 *
 * @author j0s3
 */
public class ServiceException extends RuntimeException{

    private List<String> errors;
    
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }    

    public List<String> getErrors() {
        return errors;
    }
    
}
