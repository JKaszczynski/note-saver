package com.jkaszczynski.service.note.controllers.support;

import com.jkaszczynski.service.note.controllers.models.NoteModel;
import com.jkaszczynski.service.note.documents.Note;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;

public class HateoasProcessor {

    private HateoasProcessor() {
    }

    public static NoteModel process(Note note) {
        return new NoteModel(note);
    }

    public static CollectionModel<NoteModel> process(List<Note> notes) {
        return new CollectionModel<>(notes.stream().map(NoteModel::new).collect(Collectors.toList()));
    }
}
