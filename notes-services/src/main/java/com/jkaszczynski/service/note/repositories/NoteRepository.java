package com.jkaszczynski.service.note.repositories;

import com.jkaszczynski.service.note.documents.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@PreAuthorize("hasRole('admin')")
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
}
