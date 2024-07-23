package com.scm.SCM_20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.SCM_20.services.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    @Autowired
    private OAuthenticationSuccessHandler authenticationSuccessHandler;

    /** First we create and login using Inmemory authentication */
    /*
     * @Bean
     * public UserDetailsService userDetailsService() {
     * // Here we buildings Users
     * UserDetails user1 = User
     * .withDefaultPasswordEncoder() // This default Password Encoder is not for
     * production, it is for testing
     * .username("admin")
     * .password("adminPassword")
     * .roles("ADMIN", "USER")
     * .build();
     * UserDetails user2 = User
     * .withDefaultPasswordEncoder() // This is Depricated
     * .username("user1")
     * .password("userPassword")
     * .roles("USER")
     * .build();
     * // In this object we pass the User Details
     * var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
     * user2);
     * return inMemoryUserDetailsManager;
     * }
     */

    /** Configuring Database Users */
    /*
     * Here we use Dao Authentication Provider to tells User Details
     * This @Bean is the provides the configuration of authentication provider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // This DaoAuthenticationProvider will contains all the methods with the help of
        // that methods we register our Service
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // Here we pass the Object of UserDetailsService
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // Here we pass the Object of Password Encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /*
     * With the help of this Bean we configuring the SecurityFilterChain
     * This @Bean is most important
     * This @Bean will creates a filter chain, with the filter chian we configure
     * the lots of things (all http Security)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configuring the URL's
        httpSecurity.authorizeHttpRequests(request -> {
            // request.requestMatchers("/sign-up","/login").permitAll();
            request.requestMatchers("/user/**").authenticated();
            request.anyRequest().permitAll();
        });

        /**
         * Here we use form default login
         * We customised the Form login according to our requirement
         */

        /**
         * Here we configuring the by-default login page of Spring Security
         * httpSecurity.formLogin(Customizer.withDefaults());
         */

        httpSecurity.formLogin(formLogin -> {
            // Here we configuring the our customised {login page}
            formLogin.loginPage("/login") // Our login form is in this URL /login
                    .loginProcessingUrl("/authenticate") // And the login form will submit in this URL /authenticate
                    .successForwardUrl("/user/dashboard"); // When login will success then user will forward in this url
                                                           // /user/dashboard
            // .failureForwardUrl("/login?error=true"); // When login will fail or any error
            // occure then in that case you want to forward the User in this url
            // /login?error=true
            formLogin.usernameParameter("email"); // This mean when we create the login form then the username field
                                                  // name is {email}
            formLogin.passwordParameter("password"); // This mean when we create the login form then the password field
                                                     // name is {password}
                                                     
            // formLogin.successHandler(new AuthenticationSuccessHandler() {
            //     @Override
            //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            //             Authentication authentication) throws IOException, ServletException {
            //         // TODO Auto-generated method stub
            //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
            //     }
                
            // });
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });


        // Configuring the OAuth-2 configuration
        httpSecurity.oauth2Login(oauthRequest -> {
            oauthRequest.loginPage("/login");
            oauthRequest.successHandler(authenticationSuccessHandler);
        });
        

        // This .build() will return Default Security Filter Chain and it is a type of
        // Filter Chain.
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
