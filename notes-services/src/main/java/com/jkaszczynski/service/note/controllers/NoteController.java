package com.jkaszczynski.service.note.controllers;

import com.jkaszczynski.service.note.controllers.support.HateoasProcessor;
import com.jkaszczynski.service.note.documents.Note;
import com.jkaszczynski.service.note.repositories.NoteRepository;
import com.jkaszczynski.service.note.services.UserService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RepositoryRestController
public class NoteController {

    private NoteRepository noteRepository;

    private UserService userService;

    public NoteController(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Note note) {
        note.setUsername(userService.getAuthenticatedUser());
        Note insertedNote = noteRepository.insert(note);

        return ResponseEntity.ok(HateoasProcessor.process(insertedNote));
    }

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public ResponseEntity<?> findAllByUsername(@RequestParam String username) {
        if (isUserAuthorized(username)) {
            List<Note> notes = noteRepository.findAllByUsername(username);
            return ResponseEntity.ok(HateoasProcessor.process(notes));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
    }

    private boolean isUserAuthorized(String username) {
        return username.equals(userService.getAuthenticatedUser()) || userService.hasAuthenticatedUserRoleAdmin();
    }
}
