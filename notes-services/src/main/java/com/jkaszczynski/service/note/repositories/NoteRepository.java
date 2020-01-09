package com.jkaszczynski.service.note.repositories;

import com.jkaszczynski.service.note.documents.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('admin')")
@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    @Override
    @NonNull
    @PreAuthorize("hasRole('user')")
    <S extends Note> S insert(@NonNull S s);

    @Override
    @PreAuthorize("hasRole('user')")
    @NonNull
    Optional<Note> findById(@NonNull String s);

    @Override
    @PreAuthorize("hasRole('user')")
    void deleteById(@NonNull String s);

    @PreAuthorize("hasRole('user')")
    List<Note> findAllByUsername(@NonNull String username);
}
