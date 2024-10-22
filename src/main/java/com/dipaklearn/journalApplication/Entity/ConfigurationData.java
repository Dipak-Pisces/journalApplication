package com.dipaklearn.journalApplication.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Configuration")
@Getter
@Setter
public class ConfigurationData {

    private String key;
    private String value;

}
