package com.jkaszczynski.service.note.security;

import com.jkaszczynski.service.note.security.converters.KeycloakRealmRoleConverter;
import com.jkaszczynski.service.note.security.converters.UsernameSubClaimConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@Profile("!test")
public class JwtConfiguration {

    @Value("${spring.security.oauth2.client.provider.keycloak.jwk-set-uri}")
    private String jwksUri;

    @Bean
    KeycloakRealmRoleConverter keycloakRealmRoleConverter() {
        return new KeycloakRealmRoleConverter();
    }

    @Bean
    UsernameSubClaimConverter usernameSubClaimConverter() {
        return new UsernameSubClaimConverter();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwksUri).build();
        jwtDecoder.setClaimSetConverter(usernameSubClaimConverter());
        return jwtDecoder;
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRealmRoleConverter());
        return jwtAuthenticationConverter;
    }
}
