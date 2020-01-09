package com.jkaszczynski.service.note.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final String ROLE_ADMIN = "ROLE_admin";

    public String getAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public boolean hasAuthenticatedUserRoleAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(auth -> ROLE_ADMIN.equals(auth.getAuthority()));
    }
}
