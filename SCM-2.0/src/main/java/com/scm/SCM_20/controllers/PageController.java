package com.scm.SCM_20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String home(){
        System.out.println("Home page handler.............");
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("About page handler.............");
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        System.out.println("Services page handler.............");
        return "services";
    }

}
