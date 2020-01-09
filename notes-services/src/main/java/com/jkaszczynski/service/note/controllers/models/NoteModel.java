package com.jkaszczynski.service.note.controllers.models;

import com.jkaszczynski.service.note.controllers.NoteController;
import com.jkaszczynski.service.note.documents.Note;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class NoteModel extends RepresentationModel<NoteModel> {

    @Getter
    private final Note note;

    public NoteModel(final Note note) {
        this.note = note;
        add(linkTo(methodOn(NoteController.class).insert(note)).withSelfRel());
    }
}
