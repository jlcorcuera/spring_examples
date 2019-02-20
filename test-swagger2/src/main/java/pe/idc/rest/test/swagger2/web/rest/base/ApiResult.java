/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.web.rest.base;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author j0s3
 */
public class ApiResult<T> {

    private boolean success;
    private String message;
    private T data;
    private List<String> errors;
    
    public ApiResult() {
        
    }

    public ApiResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public ApiResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }        
    
    public ApiResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }    
    
    public ApiResult(boolean success, final String message, final List<String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
    }

    public ApiResult(boolean success, final String message, final String error) {
        this.success = success;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(final List<String> errors) {
        this.errors = errors;
    }

    public void setError(final String error) {
        errors = Arrays.asList(error);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    

}