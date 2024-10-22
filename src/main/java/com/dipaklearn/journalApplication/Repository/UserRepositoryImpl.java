package com.dipaklearn.journalApplication.Repository;

import com.dipaklearn.journalApplication.Entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Queue;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserData> getUserForSA(){

        Query query = new Query();
        Criteria criteria = new Criteria();

        query.addCriteria(criteria.orOperator(
                Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"),
                Criteria.where("sentimentalAnalysis").is(true)
        ));
        List<UserData> records = mongoTemplate.find(query,UserData.class);

        return records;
    }

}
