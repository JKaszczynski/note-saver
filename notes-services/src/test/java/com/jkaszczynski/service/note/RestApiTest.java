package com.jkaszczynski.service.note;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiTest {
    //TODO
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = {"admin"})
    public void doesRestApiWork() throws Exception {
        mvc.perform(get("/api/notes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
