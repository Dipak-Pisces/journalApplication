package com.dipaklearn.journalApplication.Repository;


import com.dipaklearn.journalApplication.Entity.JournalData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalRepository extends MongoRepository<JournalData, ObjectId> {

}
