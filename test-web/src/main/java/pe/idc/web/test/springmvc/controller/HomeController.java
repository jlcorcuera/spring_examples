/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author j0s3
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
 
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
        
    @RequestMapping(method = RequestMethod.GET) 
    public String welcome(Model model) {
        model.addAttribute("serverUrl", "http://localhost:8080/test-web");
        return "home";
    }    

}
