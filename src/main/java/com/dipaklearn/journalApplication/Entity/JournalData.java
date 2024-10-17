package com.dipaklearn.journalApplication.Entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Getter
@Setter
public class JournalData {

    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

}
