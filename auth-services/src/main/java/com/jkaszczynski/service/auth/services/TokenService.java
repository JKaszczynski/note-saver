package com.jkaszczynski.service.auth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.keycloak.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String tokenUri;

    private static final String GRANT_TYPE = "password";

    private WebClient webClient;

    @PostConstruct
    private void init() {
        webClient = WebClient
                .builder()
                .baseUrl(tokenUri)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
    }

    public Map<String, Object> obtainAccessToken(String username, String password) {
        return this.webClient
                .post()
                .body(BodyInserters.fromFormData(buildRequestBody(username, password)))
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(TypedMap.class))
                .block();
    }

    private MultiValueMap<String, String> buildRequestBody(String username, String password) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("username", username);
        body.add("password", password);
        body.add("grant_type", GRANT_TYPE);

        return body;
    }

    private static class TypedMap extends HashMap<String, Object> {
    }
}
