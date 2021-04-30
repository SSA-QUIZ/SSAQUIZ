package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.dto.CoverDto;
import com.ssafy.ssaquiz.dto.WorkbookDto;
import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.model.Slide;
import com.ssafy.ssaquiz.model.Workbook;
import com.ssafy.ssaquiz.model.WorkbookRepository;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkbookService {
    @Autowired
    WorkbookRepository workbookRepository;

    public Workbook findWorkbook(String objectId) {
        return workbookRepository.findById(new ObjectId(objectId));
    }

    public WorkbookDto findWorkbookAndConvert(String objectId) {
        Workbook workbook = workbookRepository.findById(new ObjectId(objectId));
        WorkbookDto workbookDto = convertWorkbookDto(workbook);
        return workbookDto;
    }

    public WorkbookDto convertWorkbookDto(Workbook workbook) {
        if(workbook == null) {
            return null;
        }

        WorkbookDto workbookDto = WorkbookDto.builder()
                .id(workbook.getId().toString())
                .userId(workbook.getUserId())
                .workbookTitle(workbook.getWorkbookTitle())
                .slideList(workbook.getSlideList())
                .build();

        return workbookDto;
    }

    public BasicResponse insertSlide(Workbook workbook,
                                     String category, String question,
                                     String imgPath, String answer,
                                     List<String> orderedAnswer, List<String> answerList,
                                     int time, int scoreFactor, int type) {
        BasicResponse result = new BasicResponse();
        result.status = false;

        if(workbook == null) {
            result.data = "슬라이드 저장 실패 (objectId에 일치하는 문제집이 없음)";
            return result;
        }

        if("".equals(imgPath) || imgPath == null) {
            result.data = "슬라이드 저장 실패 (file 문제 발생)";
            return result;
        }

        if(category == null || question == null || answer == null || orderedAnswer == null || answerList == null) {
            result.data = "슬라이드 저장 실패 (null)";
            return result;
        }

        Slide slide = Slide.builder()
                .category(category).question(question).imagePath(imgPath)
                .answer(answer).orderedAnswer(orderedAnswer).answerList(answerList)
                .time(time).scoreFactor(scoreFactor).type(type).build();

        List<Slide> slideList = workbook.getSlideList();
        slideList.add(slide);
        workbook.setSlideList(slideList);

        workbookRepository.save(workbook);

        result.status = true;
        result.data = "슬라이드 저장 성공";
        return result;
    }

    public BasicResponse insertWorkbook(long userId, String workbookTitle) {
        BasicResponse result = new BasicResponse();

        if(workbookTitle == null) {
            result.status = false;
            result.data = "문제집 저장 실패(null)";
            return result;
        }

        Workbook workbook = Workbook.builder()
                .workbookTitle(workbookTitle)
                .userId(userId)
                .slideList(new ArrayList<Slide>())
                .build();

        workbookRepository.save(workbook);

        result.status = true;
        result.data = "문제집 저장 성공";
        result.object = convertWorkbookDto(workbook);
        return result;
    }

    public List<Workbook> findAllWorkbook(long userId) {
        return workbookRepository.findByUserId(userId);
    }

    public BasicResponse findIdAndTitleByWorkbookList(List<Workbook> workbookList) {
        BasicResponse result = new BasicResponse();

        if(workbookList == null) {
            result.status = false;
            result.data = "문제집 조회 실패(null)";
            return result;
        }

        List<CoverDto> CoverDtoList = new ArrayList<>();
        for (Workbook workbook : workbookList) {
            CoverDtoList.add(CoverDto.builder().id(workbook.getId().toString()).title(workbook.getWorkbookTitle()).build());
        }
        Collections.reverse(CoverDtoList);

        result.status = true;
        result.data = "문제집 조회 성공";
        result.object = CoverDtoList;
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
