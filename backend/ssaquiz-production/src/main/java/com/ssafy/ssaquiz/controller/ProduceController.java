package com.ssafy.ssaquiz.controller;

import com.ssafy.ssaquiz.dto.InsertWorkbookDto;
import com.ssafy.ssaquiz.dto.WorkbookDto;
import com.ssafy.ssaquiz.model.*;
import com.ssafy.ssaquiz.service.S3Service;
import com.ssafy.ssaquiz.service.WorkbookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
public class ProduceController {
    @Autowired
    WorkbookService workbookService;

    @Autowired
    private S3Service s3Service;

    @ApiOperation(value = "슬라이드 저장하기")
    @PostMapping("/slide")
    public Object insertSlide(@RequestParam("objectId") String objectId, @RequestParam("category") String category,
                              @RequestParam("question") String question, @RequestParam("file") MultipartFile inputFile,
                              @RequestParam("answer") String answer, @RequestParam("orderedAnswer") List<String> orderedAnswer,
                              @RequestParam("answerList") List<String> answerList, @RequestParam("time") int time,
                              @RequestParam("scoreFactor") int scoreFactor, @RequestParam("type") int type) {
        return workbookService.saveSlide(objectId, category, question, inputFile, answer,
                orderedAnswer, answerList, time, scoreFactor, type);
    }

    @ApiOperation(value = "문제집 조회하기",
            notes = "[sample data] 60950c5d9ea601323d76346f, 6093886dc3583527b674ca2e"
    )
    @GetMapping("/workbook/{objectId}")
    public BasicResponse findWorkbook(@PathVariable("objectId") String objectId) {
        return workbookService.lookupWorkbook(objectId);
    }

    @ApiOperation(value = "문제집 만들기")
    @PostMapping("/workbook")
    public Object insertWorkbook(@RequestBody InsertWorkbookDto dto) {
        return workbookService.insertWorkbook(dto.getUserId(), dto.getWorkbookTitle());
    }

    @ApiOperation(value = "전체 문제집 조회하기",
            notes = "[sample data] 8282"
    )
    @GetMapping("/workbook-all/{userId}")
    public Object findAllWorkbook(@PathVariable("userId") long userId) {
        List<Workbook> workbookList = workbookService.findAllWorkbook(userId);
        return workbookService.findIdAndTitleByWorkbookList(workbookList);
    }

    @ApiOperation(value = "이미지 저장하기")
    @PostMapping("/image")
    public Object insertImage(@RequestParam("file") MultipartFile inputFile) {
        return workbookService.insertImage(inputFile);
    }

    @ApiOperation(value = "문제집 저장하기")
    @PostMapping("/slide-all")
    public Object insertWorkbook(@RequestBody WorkbookDto workbookDto) {
        return workbookService.insertWorkbook(workbookDto);
    }

    @ApiOperation(value = "문제집 삭제하기")
    @DeleteMapping("/workbook/{objectId}/{userId}")
    public Object deleteWorkbook(@PathVariable("objectId") String objectId, @PathVariable("userId") long userId) {
        return workbookService.deleteWorkbook(objectId, userId);
    }

    @ApiOperation(value = "슬라이드 삭제하기")
    @DeleteMapping("/workbook/{objectId}/{userId}/{slideIndex}")
    public Object deleteSlide(@PathVariable("objectId") String objectId, @PathVariable("userId") long userId, @PathVariable("slideIndex") int slideIndex) {
        return workbookService.deleteSlide(objectId, userId, slideIndex);
    }
}
