package com.jkaszczynski.service.note.documents;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document
@Getter
@Setter
public class Note {
    @Id
    private String id;

    private String username;

    private String note;

    private Set<String> tags;

    private LocalDateTime date = LocalDateTime.now();
}
