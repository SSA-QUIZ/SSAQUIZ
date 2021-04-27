package com.ssafy.ssaquiz.controller;

import com.ssafy.ssaquiz.model.*;
import com.ssafy.ssaquiz.service.WorkbookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RequestMapping("/produce")
@RestController
public class ProduceController {
    @Autowired
    WorkbookService workbookService;

    @ApiOperation(value = "슬라이드 저장하기")
    @PostMapping("/insertSlide")
    public Object insertSlide(@RequestParam("objectId") String objectId, @RequestParam("category") String category,
                              @RequestParam("question") String question, @RequestParam("file") MultipartFile inputFile,
                              @RequestParam("answer") String answer, @RequestParam("orderedAnswer") List<String> orderedAnswer,
                              @RequestParam("answerList") List<String> answerList, @RequestParam("time") int time,
                              @RequestParam("scoreFactor") int scoreFactor, @RequestParam("type") int type) {
        Workbook workbook = workbookService.findWorkbook(objectId);
        return workbookService.insertSlide(workbook, category, question, inputFile, answer,
                                           orderedAnswer, answerList, time, scoreFactor, type);
    }

    @ApiOperation(value = "문제집 검색하기")
    @PostMapping("/findWorkbook")
    public Workbook findWorkbook(@RequestParam("objectId") String objectId) {
        return workbookService.findWorkbook(objectId);
    }

    @ApiOperation(value = "문제집 만들기")
    @PostMapping("/insertWorkbook")
    public Object insertWorkbook(@RequestParam("workbookTitle") String workbookTitle, @RequestParam("userId") long userId,
                                 @RequestParam("category") String category, @RequestParam("question") String question,
                                 @RequestParam("file") MultipartFile inputFile, @RequestParam("answer") String answer,
                                 @RequestParam("orderedAnswer") List<String> orderedAnswer, @RequestParam("answerList") List<String> answerList,
                                 @RequestParam("time") int time, @RequestParam("scoreFactor") int scoreFactor,
                                 @RequestParam("type") int type) {
        return workbookService.insertWorkbook(workbookTitle, userId, category, question, inputFile,
                                              answer, orderedAnswer, answerList, time, scoreFactor, type);
    }
}
