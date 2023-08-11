package com.luv2code.springboot.cruddemo.security;

import java.beans.BeanProperty;
import java.security.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails shiv = User.builder()
                .username("shiv")
                .password("{noop}passwordSh")
                .roles("EMPLOYEE")
                .build();
        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}passwordMa")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}passwordSu")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(shiv, susan, mary);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( configurer ->
                configurer
                  .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                  .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                  .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                  .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                  .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );
        //  THIS INSTRUCTS TO USE BASIC AUTHENTICATION
        http.httpBasic(Customizer.withDefaults());

        // THIS INSTRUCTS TO DISABLE CSFR
        http.csrf(customiser -> customiser.disable());

        return http.build();
    }
}
