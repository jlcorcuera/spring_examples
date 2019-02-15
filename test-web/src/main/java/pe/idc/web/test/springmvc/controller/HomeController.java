/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pe.idc.web.test.springmvc.util.SecurityUtils;

/**
 *
 * @author j0s3
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {
 
    @Value("${server_location}")
    private String serverUrl;
    
    
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
        
    @RequestMapping(value = "/admin", method = RequestMethod.GET)     
    public String welcomeAdmin(Model model) {
        model.addAttribute("serverUrl", serverUrl);
        return "admin";
    }    

    @RequestMapping(value = "/user", method = RequestMethod.GET)     
    public String welcomeUser(Model model) {
        model.addAttribute("serverUrl", serverUrl);
        return "user";
    }        
    
    @RequestMapping(value = "/route", method = RequestMethod.GET)     
    public String route(Model model) {
        boolean isAdmin = SecurityUtils.hasRole("ROLE_ADMIN");
        if (isAdmin){
            return "redirect:/pages/home/admin";
        }
        return "redirect:/pages/home/user";
    }            
    
}
