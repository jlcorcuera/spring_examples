/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.rest.test.swagger2.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.idc.rest.test.swagger2.dto.UserDTO;
import pe.idc.rest.test.swagger2.security.model.JwtUserDTO;
import pe.idc.rest.test.swagger2.service.UserService;
import pe.idc.rest.test.swagger2.web.rest.base.ApiResult;
/**
 *
 * @author j0s3
 */
@RestController
@RequestMapping(value = "/login")
@Api("Login")
public class PublicRest {

    private static final Logger log = LoggerFactory.getLogger(PublicRest.class);
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json") 
    @ApiOperation("Login users")    
    @ResponseBody
    public ApiResult<JwtUserDTO> login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        log.info("Login user method");
        JwtUserDTO jwtUserDTO = userService.login(userDTO);
        ApiResult<JwtUserDTO> result = null;
        if (jwtUserDTO != null){
            result = new ApiResult<JwtUserDTO>(true, jwtUserDTO);
        }else{
            result = new ApiResult<JwtUserDTO>(false, "Verify your credentials");
        }
        return result;
    }
    
}
