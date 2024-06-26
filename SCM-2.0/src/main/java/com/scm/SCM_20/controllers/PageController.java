package com.scm.SCM_20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String home(){
        System.out.println("Home page handler.............");
        return new String("home");
    }

    @RequestMapping("/about")
    public String about(){
        System.out.println("About page handler.............");
        return new String("about");
    }

    @RequestMapping("/services")
    public String services(){
        System.out.println("Services page handler.............");
        return new String("services");
    }

    @RequestMapping("/contact")
    public String contact(){
        System.out.println("Contact page handler.............");
        return new String("contact");
    }

    @RequestMapping("/sign-up")
    public String signUp(){
        System.out.println("Sing-up page handler.............");
        return new String("register");
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("Login page handler.............");
        return new String("login");
    }

}
