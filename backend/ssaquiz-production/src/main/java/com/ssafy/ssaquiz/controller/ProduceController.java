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
    @PostMapping("/slide")
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

    @ApiOperation(value = "문제집 조회하기",
            notes = "[sample data] 6088e1e504228a182a4159e3, 6088fb4792ab8f759867aefe"
    )
    @GetMapping("/workbook/{objectId}")
    public BasicResponse findWorkbook(@PathVariable("objectId") String objectId) {
        BasicResponse result = new BasicResponse();

        result.status = true;
        result.data = "문제집 조회 성공";
        result.object = workbookService.findWorkbookAndConvert(objectId);

        return result;
    }

    @ApiOperation(value = "문제집 만들기")
    @PostMapping("/workbook")
    public Object insertWorkbook(@RequestParam("userId") long userId, @RequestParam("workbookTitle") String workbookTitle) {
        return workbookService.insertWorkbook(userId, workbookTitle);
    }

    @ApiOperation(value = "전체 문제집 조회하기",
            notes = "[sample data] 1, 2"
    )
    @GetMapping("/workbook-all/{userId}")
    public Object findAllWorkbook(@PathVariable("userId") long userId) {
        List<Workbook> workbookList = workbookService.findAllWorkbook(userId);
        return workbookService.findIdAndTitleByWorkbookList(workbookList);
    }
}
