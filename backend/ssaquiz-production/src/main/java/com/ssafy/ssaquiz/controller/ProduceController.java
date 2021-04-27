package com.ssafy.ssaquiz.controller;

import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.service.WorkbookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = { "*" })
@RequestMapping("/produce")
@RestController
public class ProduceController {
    @Autowired
    WorkbookService workbookService;

    @ApiOperation(value = "슬라이드 저장하기")
    @PostMapping
    public Object insertSlide(@RequestParam("workbookId") int workbookId, @RequestParam("userId") int userId,
                              @RequestParam("answer") String answer, @RequestParam("orderedAnswer") List<String> orderedAnswer,
                              @RequestParam("answerList") List<String> answerList, @RequestParam("category") String category,
                              @RequestParam("question") String question, @RequestParam("file") MultipartFile inputFile,
                              @RequestParam("workbookTitle") String workbookTitle, @RequestParam("settings") Map<String, String> settings){
        BasicResponse result = new BasicResponse();
        workbookService.findWorkbook(workbookId, userId);
        return result;
    }
}
