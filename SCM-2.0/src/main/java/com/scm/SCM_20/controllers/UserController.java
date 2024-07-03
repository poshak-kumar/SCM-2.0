package com.scm.SCM_20.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    /*** Here we create the views related to user */

    @RequestMapping("/dashboard")
    public String userDashBoard(){
        System.out.println("User dashboard");
        return new String("user/dashboard");
    }

    @RequestMapping("/profile")
    public String userProfile(){
        System.out.println("User profile");
        return new String("user/profile");
    }


}
