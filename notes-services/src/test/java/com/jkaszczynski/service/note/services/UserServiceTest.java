package com.jkaszczynski.service.note.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private final String username = "testUsername";
    private final String differentUsername = "differentTestUsername";


    @Test
    @WithMockUser(roles = {"user"}, username = username)
    public void givenUsername_whenAuthorized_thenReturnTrue() {
        boolean isAuthorized = userService.isUserAuthorized(username);
        Assertions.assertThat(isAuthorized).isEqualTo(true);
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void givenUsername_whenAuthenticatedAsAdmin_thenReturnTrue() {
        boolean isAuthorized = userService.isUserAuthorized(username);
        Assertions.assertThat(isAuthorized).isEqualTo(true);
    }

    @Test
    public void givenUsername_whenUnauthenticated_thenThrowException() {
        assertThrows(NullPointerException.class, () ->
                userService.isUserAuthorized(username)
        );
    }

    @Test
    @WithMockUser(roles = {"user"}, username = differentUsername)
    public void givenUsername_whenUnauthorized_thenReturnFalse() {
        boolean isAuthorized = userService.isUserAuthorized(username);
        Assertions.assertThat(isAuthorized).isEqualTo(false);
    }
}
