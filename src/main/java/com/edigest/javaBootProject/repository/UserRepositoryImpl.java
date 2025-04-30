package com.edigest.javaBootProject.repository;

import com.edigest.javaBootProject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSA(){
        Query query = new Query();
        Criteria criteria = new Criteria();
//        query.addCriteria(Criteria.where("userName").is("johndoe"));
        query.addCriteria(criteria.andOperator(
                Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"),
                Criteria.where("sentimentAnalysis").is(true)));
        return mongoTemplate.find(query, User.class);

    }
}
