package com.jkaszczynski.service.note.documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
public class Note {
    @Id
    private String id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String username;

    private String note;

    private Set<String> tags = new HashSet<>();

    private LocalDateTime date = LocalDateTime.now();
}
