package com.dipaklearn.journalApplication.Repository;

import com.dipaklearn.journalApplication.Entity.ConfigurationData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<ConfigurationData, ObjectId> {

}
