package com.edigest.javaBootProject.repository;

import com.edigest.javaBootProject.entity.ConfigJournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalEntity, ObjectId> {

}
