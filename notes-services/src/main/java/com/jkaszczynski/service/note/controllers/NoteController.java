package com.jkaszczynski.service.note.controllers;

import com.jkaszczynski.service.note.documents.Note;
import com.jkaszczynski.service.note.repositories.NoteRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class NoteController {

    private NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/notes", method = RequestMethod.POST)
    public ResponseEntity<?> addNote(Note note) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        note.setUsername(username);
        Note updatedNote = noteRepository.insert(note);

        return ResponseEntity.ok(buildHateoasResponse(updatedNote));
    }

    private EntityModel<Note> buildHateoasResponse(Note note) {
        EntityModel<Note> entityModel = new EntityModel<>(note);
        return entityModel.add(linkTo(methodOn(NoteController.class).addNote(note)).withSelfRel());
    }
}
