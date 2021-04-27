package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.model.Workbook;
import com.ssafy.ssaquiz.model.WorkbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
public class WorkbookService {
    @Autowired
    WorkbookRepository workbookRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Workbook findWorkbook(long workbookId, long userId) {
        Workbook workbook = workbookRepository.findByIdAndUserId(workbookId, userId);
        return workbook;
    }
}
