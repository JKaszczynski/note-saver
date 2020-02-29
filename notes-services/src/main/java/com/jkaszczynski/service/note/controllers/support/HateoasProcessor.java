package com.jkaszczynski.service.note.controllers.support;

import com.jkaszczynski.service.note.controllers.models.NoteModel;
import com.jkaszczynski.service.note.documents.Note;

public class HateoasProcessor {

    private HateoasProcessor() {
    }

    public static NoteModel process(Note note) {
        return new NoteModel(note);
    }
}
