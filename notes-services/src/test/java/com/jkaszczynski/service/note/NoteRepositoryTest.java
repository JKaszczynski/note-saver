package com.jkaszczynski.service.note;

import com.jkaszczynski.service.note.documents.Note;
import com.jkaszczynski.service.note.repositories.NoteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


@DataMongoTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void doesNoteRepositoryWork() {
        Note note = new Note();
        note.setNote("lul");
        note.setUsername("123");

        noteRepository.insert(note);

        Assertions.assertThat(noteRepository.findAll()).hasSize(1);
    }
}
