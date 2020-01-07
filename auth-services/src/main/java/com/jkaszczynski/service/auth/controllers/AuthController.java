package com.jkaszczynski.service.auth.controllers;

import com.jkaszczynski.service.auth.services.TokenService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @RequestMapping(path = "/oauth/token", method = RequestMethod.POST)
    public Map<String, Object> obtainAccessToken(String username, String password) {
        return tokenService.obtainAccessToken(username, password);
    }
}
