package com.jkaszczynski.service.note.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class JwtConfiguration {
    @Bean
    JwtDecoder jwtDecoder() {
        return mock(JwtDecoder.class);
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        return mock(JwtAuthenticationConverter.class);
    }
}