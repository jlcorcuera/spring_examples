/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.web.rest.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import pe.idc.rest.test.swagger2.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author j0s3
 */
@ControllerAdvice
public class RestExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(value = {ServiceException.class, Exception.class})
    @ResponseBody
    protected ApiResult handleServiceException(ServiceException ex, WebRequest request) {
        log.error("ServiceException", ex);
        return new ApiResult(false, ex.getMessage(), ex.getErrors());
    }

}
