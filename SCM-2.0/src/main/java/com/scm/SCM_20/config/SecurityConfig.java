package com.scm.SCM_20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    /** First we create and login using Inmemory authentication */

    @Bean
    public UserDetailsService userDetailsService() {
        // Here we buildings Users
        UserDetails user1 = User
                .withDefaultPasswordEncoder() // This default Password Encoder is not for production, it is for testing
                .username("admin")
                .password("adminPassword")
                .roles("ADMIN", "USER")
                .build();
        UserDetails user2 = User
                .withDefaultPasswordEncoder() // This is Depricated
                .username("user1")
                .password("userPassword")
                .roles("USER")
                .build();
        // In this object we pass the User Details
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user2);

        return inMemoryUserDetailsManager;
    }

}
