package com.jkaszczynski.service.note.security.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.jwt.MappedJwtClaimSetConverter;

import java.util.Collections;
import java.util.Map;

public class UsernameSubClaimConverter implements Converter<Map<String, Object>, Map<String, Object>> {

    private final MappedJwtClaimSetConverter claimSetConverter = MappedJwtClaimSetConverter.withDefaults(Collections.emptyMap());
    private static final String USERNAME_CLAIM = "preferred_username";

    @Override
    public Map<String, Object> convert(@NonNull Map<String, Object> claims) {
        Map<String, Object> convertedClaims = this.claimSetConverter.convert(claims);
        String username = (String) convertedClaims.get(USERNAME_CLAIM);
        convertedClaims.put("sub", username);
        return convertedClaims;
    }
}
