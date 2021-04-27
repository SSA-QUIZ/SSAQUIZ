package com.ssafy.ssaquiz.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkbookRepository extends MongoRepository<Workbook, String> {
    Workbook findById(ObjectId id);
}
