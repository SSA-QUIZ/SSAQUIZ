package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.model.Slide;
import com.ssafy.ssaquiz.model.Workbook;
import com.ssafy.ssaquiz.model.WorkbookRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class WorkbookService {
    @Autowired
    WorkbookRepository workbookRepository;

    public Workbook findWorkbook(String objectId) {
        Workbook workbook = workbookRepository.findById(new ObjectId(objectId));
        return workbook;
    }

    public BasicResponse insertSlide(Workbook workbook,
                                     String category, String question,
                                     String imgPath, String answer,
                                     List<String> orderedAnswer, List<String> answerList,
                                     int time, int scoreFactor, int type) {
        Slide slide = Slide.builder()
                .category(category).question(question).imagePath(imgPath)
                .answer(answer).orderedAnswer(orderedAnswer).answerList(answerList)
                .time(time).scoreFactor(scoreFactor).type(type).build();

        List<Slide> slideList = workbook.getSlideList();
        slideList.add(slide);
        workbook.setSlideList(slideList);

        workbookRepository.save(workbook);

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "슬라이드 저장 성공";
        return result;
    }

    public BasicResponse insertWorkbook(String workbookTitle, long userId,
                                        String category, String question,
                                        String imgPath, String answer,
                                        List<String> orderedAnswer, List<String> answerList,
                                        int time, int scoreFactor, int type) {

        Slide slide = Slide.builder()
                .category(category).question(question).imagePath(imgPath)
                .answer(answer).orderedAnswer(orderedAnswer).answerList(answerList)
                .time(time).scoreFactor(scoreFactor).type(type).build();

        Workbook workbook = Workbook.builder()
                .workbookTitle(workbookTitle)
                .userId(userId)
                .slideList(List.of(slide))
                .build();

        workbookRepository.save(workbook);

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "문제집 저장 성공";
        return result;
    }

}
