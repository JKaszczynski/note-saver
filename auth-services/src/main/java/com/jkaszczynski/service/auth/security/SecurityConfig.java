package com.jkaszczynski.service.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "admin";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/oauth/token/**")
                                .permitAll()
                                .anyRequest()
                                .hasRole(ROLE_ADMIN))
                .csrf().disable();
    }
}
