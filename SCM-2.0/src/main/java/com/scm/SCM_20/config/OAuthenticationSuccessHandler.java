package com.scm.SCM_20.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.SCM_20.entities.Providers;
import com.scm.SCM_20.entities.User;
import com.scm.SCM_20.helpers.AppConstants;
import com.scm.SCM_20.repositories.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    Logger logger = LoggerFactory.getLogger(OAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
                logger.info("OAuthenticationSuccessHandler class");

                DefaultOAuth2User dAuth2User = (DefaultOAuth2User)authentication.getPrincipal();

                // logger.info(dAuth2User.getName());
                // dAuth2User.getAttributes().forEach((key,value)->{
                //     logger.info("{} : {}",key,value);
                // });
                // logger.info(dAuth2User.getAuthorities().toString());

                String name = dAuth2User.getAttribute("name");
                String email = dAuth2User.getAttribute("email");
                String picture = dAuth2User.getAttribute("picture");
                String userProviderId = dAuth2User.getName();
                
                // Creating the User
                User user = new User();
                user.setUserId(UUID.randomUUID().toString());
                user.setName(name);
                user.setEmail(email);
                user.setProfilePicLink(picture);
                user.setProvider(Providers.GOOGLE);
                user.setPassword("password");
                user.setEnabled(true);
                user.setEmailVerified(true);
                user.setProviderUserId(userProviderId);
                user.setRolesList(List.of(AppConstants.ROLE_USER));
                user.setAbout("Account created by 'GOOGLE' provider, using OAuth-2");
                // Save the data on Database
                User checkingUser = userRepository.findByEmail(email).orElse(null);
                if (checkingUser == null) {
                    userRepository.save(user);
                    logger.info("User saved successfully "+ email);
                }

                // response.sendRedirect("/home");
                new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}
