package com.jkaszczynski.service.note.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jkaszczynski.service.note.documents.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String username = "testUsername";
    private final String noteContent = "test note content";


    @Test
    @WithMockUser(roles = {"user"}, username = username)
    public void givenNote_whenInserted_thenCreatedStatus() throws Exception {
        mvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getSampleNote())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("note.username").value(username))
                .andExpect(jsonPath("note.note").value(noteContent));
    }

    private Note getSampleNote() {
        Note note = new Note();
        note.setNote(noteContent);
        note.setUsername(this.username);
        return note;
    }

    @Test
    public void givenNote_whenInserted_thenUnauthorizedStatus() throws Exception {
        mvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getSampleNote())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = {"user"}, username = username)
    public void givenUser_whenRequestForAllNotes_thenReturnAllNotes() throws Exception {
        mvc.perform(get("/notes/search/username")
                .param("username", this.username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"admin"})
    public void givenAdmin_whenRequestForAllNotes_thenReturnAllNotes() throws Exception {
        mvc.perform(get("/notes/search/username")
                .param("username", this.username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenRequestForAllNotes_thenUnauthorizedStatus() throws Exception {
        mvc.perform(get("/notes/search/username")
                .param("username", this.username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
