package com.ssafy.ssaquiz.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkbookRepository extends MongoRepository<Workbook, String> {
    Workbook findById(ObjectId id);
    List<Workbook> findByUserId(long userId);
}
