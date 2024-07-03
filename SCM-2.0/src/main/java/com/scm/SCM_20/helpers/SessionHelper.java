package com.scm.SCM_20.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage() {

        try {
            // With the help of this line we get the Session Object
            HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getSession();
            httpSession.removeAttribute("message");
            System.out.println("Removing message from session successful");
        } catch (Exception e) {
            System.out.println("Can't remove the {message} attribute " + e);
            e.getMessage();
            e.printStackTrace();
        }

    }

}
