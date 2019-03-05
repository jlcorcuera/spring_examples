/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author j0s3
 */
public class JwtTokenMissingException extends AuthenticationException {


    public JwtTokenMissingException(String msg) {
        super(msg);
    }
}