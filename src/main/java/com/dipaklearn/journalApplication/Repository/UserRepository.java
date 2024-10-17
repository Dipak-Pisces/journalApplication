package com.dipaklearn.journalApplication.Repository;

import com.dipaklearn.journalApplication.Entity.UserData;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserData, ObjectId> {
    UserData findUserByUsername(String username);
    void deleteByUsername(String username);
}
