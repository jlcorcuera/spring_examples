/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.controller.base;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import pe.idc.web.test.springmvc.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author j0s3
 */
@ControllerAdvice
public class WebExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(value = {ServiceException.class})
    @ResponseBody
    protected ApiResult handleServiceException(ServiceException ex, WebRequest request) {
        log.error("ServiceException", ex);
        return new ApiResult(false, ex.getMessage(), ex.getErrors());
    }

}
