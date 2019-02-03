/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
/**
 *
 * @author j0s3
 */
@Controller
@RequestMapping(value = "/public")
public class PublicController {

    @Value("${server_location}")
    private String serverUrl;
    
    @Value("${open_ui_location}")
    private String openUIUrl;    
    
    private static final Logger log = LoggerFactory.getLogger(PublicController.class);
        
    @RequestMapping(value = "/login", method = RequestMethod.GET) 
    public String welcome(Model model) {
        model.addAttribute("serverUrl", serverUrl);
        model.addAttribute("openUIUrl", openUIUrl);
        return "index";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET) 
    public String registration(Model model) {
        model.addAttribute("serverUrl", serverUrl);
        model.addAttribute("openUIUrl", openUIUrl);
        return "registration";
    }    
    
}
