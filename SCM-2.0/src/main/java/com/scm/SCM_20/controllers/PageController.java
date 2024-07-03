package com.scm.SCM_20.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.SCM_20.entities.User;
import com.scm.SCM_20.forms.UserForm;
import com.scm.SCM_20.helpers.Message;
import com.scm.SCM_20.helpers.MessageTypes;
import com.scm.SCM_20.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    // Here we also use constructor injection and it is recommended
    @Autowired 
    private UserService userService;

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
    public String signUp(Model model){
        System.out.println("Sing-up page handler.............");

        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return new String("register");
    }

    @RequestMapping("/login")
    public String login(){
        System.out.println("Login page handler.............");
        return new String("login");
    }


    // Processing or Handling the Sign-up form
    @PostMapping("/do-register")
    public String registerProcessHandler(@Valid @ModelAttribute UserForm userForm , BindingResult bindingResult ,HttpSession httpSession){
        System.out.println("Processing register form.........");
        // Fetch the data from the Sign-up form
        System.out.println(userForm);
        // Sign-up form validation
        if (bindingResult.hasErrors()) {
            return new String("register");
        }

        // UserForm -> User
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNum(userForm.getPhoneNum());
        user.setAbout(userForm.getAbout());
        user.setProfilePicLink("https://upload.wikimedia.org/wikipedia/commons/b/b5/Windows_10_Default_Profile_Picture.svg");

        // Save the Data on Database
        userService.saveUser(user);
        System.out.println("User saved successfuly....");

        // Adding the Success message
        Message message = Message.builder().content("Registration Successful").types(MessageTypes.green).build();
        httpSession.setAttribute("message", message);

        // redirect on sign-up or login page
        return "redirect:/sign-up";
    }

}
