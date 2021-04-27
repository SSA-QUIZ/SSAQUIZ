package com.ssafy.ssaquiz.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkbookRepository extends MongoRepository<Workbook, Long> {
    Workbook findByIdAndUserId(long id, long userId);
}
