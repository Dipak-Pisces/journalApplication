package com.dipaklearn.journalApplication.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
@Builder
public class UserData {

    @Id
    private ObjectId ID;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @NonNull
    private String password;

    private List<JournalData> journal_entries = new ArrayList<>();

    private List<String> roles;
}