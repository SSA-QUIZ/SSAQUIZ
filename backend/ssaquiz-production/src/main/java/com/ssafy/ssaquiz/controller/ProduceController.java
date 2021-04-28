package com.ssafy.ssaquiz.controller;

import com.ssafy.ssaquiz.model.*;
import com.ssafy.ssaquiz.service.S3Service;
import com.ssafy.ssaquiz.service.WorkbookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RequestMapping("/produce")
@RestController
public class ProduceController {
    @Autowired
    WorkbookService workbookService;

    @Autowired
    private S3Service s3Service;

    @ApiOperation(value = "슬라이드 저장하기")
    @PostMapping("/insertSlide")
    public Object insertSlide(@RequestParam("objectId") String objectId, @RequestParam("category") String category,
                              @RequestParam("question") String question, @RequestParam("file") MultipartFile inputFile,
                              @RequestParam("answer") String answer, @RequestParam("orderedAnswer") List<String> orderedAnswer,
                              @RequestParam("answerList") List<String> answerList, @RequestParam("time") int time,
                              @RequestParam("scoreFactor") int scoreFactor, @RequestParam("type") int type) {
        Workbook workbook = workbookService.findWorkbook(objectId);
        String imgPath = s3Service.upload(inputFile);
        return workbookService.insertSlide(workbook, category, question, imgPath, answer,
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
        String imgPath = s3Service.upload(inputFile);
        return workbookService.insertWorkbook(workbookTitle, userId, category, question, imgPath,
                                              answer, orderedAnswer, answerList, time, scoreFactor, type);
    }

    @ApiOperation(value = "문제집 조회")
    @PostMapping("/findAllWorkbook")
    public Object findAllWorkbook(@RequestParam("userId") long userId) {
        List<Workbook> workbookList = workbookService.findAllWorkbook(userId);
        return workbookService.findIdAndTitleByWorkbookList(workbookList);
    }
}