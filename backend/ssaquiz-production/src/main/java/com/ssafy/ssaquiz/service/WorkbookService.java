package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.dto.CoverDto;
import com.ssafy.ssaquiz.dto.WorkbookDto;
import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.model.Slide;
import com.ssafy.ssaquiz.model.Workbook;
import com.ssafy.ssaquiz.model.WorkbookRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WorkbookService {
    @Autowired
    WorkbookRepository workbookRepository;

    @Autowired
    private S3Service s3Service;

    public BasicResponse saveSlide(String objectId, String category,
                                   String question, MultipartFile inputFile,
                                   String answer, List<String> orderedAnswer,
                                   List<String> answerList, int time,
                                   float scoreFactor, String type) {
        BasicResponse result = new BasicResponse();
        result.status = false;

        if (objectId == null) {
            result.data = "slide save fail (objectId is null)";
            return result;
        }

        if (ObjectId.isValid(objectId) == false) {
            result.data = "slide save fail (objectId is not valid)";
            return result;
        }

        Workbook workbook = findWorkbook(objectId);
        String imgPath = s3Service.upload(inputFile);

        return insertSlide(workbook, category, question, imgPath, answer,
                orderedAnswer, answerList, time, scoreFactor, type);
    }

    public BasicResponse lookupWorkbook(String objectId) {
        BasicResponse result = new BasicResponse();

        if (objectId == null) {
            result.status = false;
            result.data = "workbook lookup fail (objectId is null)";
            return result;
        }

        if (ObjectId.isValid(objectId) == false) {
            result.data = "workbook lookup fail (objectId is not valid)";
            return result;
        }

        result.status = true;
        result.data = "workbook lookup success";
        result.object = findWorkbookAndConvert(objectId);

        return result;
    }

    public Workbook findWorkbook(String objectId) {
        return workbookRepository.findById(new ObjectId(objectId));
    }

    public WorkbookDto findWorkbookAndConvert(String objectId) {
        Workbook workbook = workbookRepository.findById(new ObjectId(objectId));
        WorkbookDto workbookDto = convertWorkbookDto(workbook);
        return workbookDto;
    }

    public WorkbookDto convertWorkbookDto(Workbook workbook) {
        if (workbook == null) {
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
                                     int time, float scoreFactor, String type) {
        BasicResponse result = new BasicResponse();
        result.status = false;

        if (workbook == null || imgPath == null || category == null || question == null || answer == null || orderedAnswer == null || answerList == null) {
            result.data = "slide save fail (null)";
            return result;
        }

        if ("".equals(imgPath)) {
            result.data = "slide save fail (file upload fail)";
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
        result.data = "slide save success";
        return result;
    }

    public BasicResponse insertWorkbook(long userId, String workbookTitle) {
        BasicResponse result = new BasicResponse();

        if (workbookTitle == null) {
            result.status = false;
            result.data = "workbook save fail (workbookTitle is null)";
            return result;
        }

        Workbook workbook = Workbook.builder()
                .workbookTitle(workbookTitle)
                .userId(userId)
                .slideList(new ArrayList<Slide>())
                .build();

        workbookRepository.save(workbook);

        result.status = true;
        result.data = "workbook save success";
        result.object = convertWorkbookDto(workbook);
        return result;
    }

    public List<Workbook> findAllWorkbook(long userId) {
        return workbookRepository.findByUserId(userId);
    }

    public BasicResponse findIdAndTitleByWorkbookList(List<Workbook> workbookList) {
        BasicResponse result = new BasicResponse();

        if (workbookList == null) {
            result.status = false;
            result.data = "workbook lookup fail (workbookList is null)";
            return result;
        }

        List<CoverDto> CoverDtoList = new ArrayList<>();
        for (Workbook workbook : workbookList) {
            CoverDtoList.add(CoverDto.builder().id(workbook.getId().toString()).title(workbook.getWorkbookTitle()).build());
        }
        Collections.reverse(CoverDtoList);

        result.status = true;
        result.data = "workbook lookup success";
        result.object = CoverDtoList;
        return result;
    }

    public BasicResponse insertImage(MultipartFile inputFile) {
        String imgPath = s3Service.upload(inputFile);
        BasicResponse result = new BasicResponse();
        result.status = false;

        if (inputFile == null) {
            result.data = "file save fail (file is null)";
            return result;
        }

        if ("".equals(imgPath)) {
            result.data = "slide save fail (file upload fail)";
            return result;
        }

        result.status = true;
        result.data = "slide save success";
        result.object = imgPath;
        return result;
    }

    public BasicResponse insertWorkbook(WorkbookDto workbookDto) {
        BasicResponse result = new BasicResponse();
        result.status = false;

        if (workbookDto == null || workbookDto.getId() == null) {
            result.data = "workbook save fail (null)";
            return result;
        }

        if (ObjectId.isValid(workbookDto.getId()) == false) {
            result.data = "workbook save fail (objectId is not valid)";
            return result;
        }

        Workbook workbookById = workbookRepository.findById(new ObjectId(workbookDto.getId()));
        if (workbookById == null || workbookById.getUserId() != workbookDto.getUserId()) {
            result.data = "workbook save fail";
            return result;
        }

        Workbook workbook = Workbook.builder()
                .id(new ObjectId(workbookDto.getId()))
                .userId(workbookDto.getUserId())
                .workbookTitle(workbookDto.getWorkbookTitle())
                .slideList(workbookDto.getSlideList())
                .build();

        workbookRepository.save(workbook);

        result.status = true;
        result.data = "workbook save success";
        return result;
    }

    @Transactional
    public BasicResponse deleteWorkbook(String objectId, long userId) {
        BasicResponse result = new BasicResponse();
        result.status = false;
        result.data = "workbook delete fail";

        if (objectId == null) {
            result.data = "workbook delete fail (objectId is null)";
            return result;
        }

        if (ObjectId.isValid(objectId) == false) {
            result.data = "workbook delete fail (objectId is not valid)";
            return result;
        }

        long deleteCnt = workbookRepository.deleteByIdAndUserId(new ObjectId(objectId), userId);
        if(deleteCnt != 0){
            result.status = true;
            result.data = "workbook delete success";
            return result;
        }

        return result;
    }

    public BasicResponse deleteSlide(String objectId, long userId, int slideIndex) {
        BasicResponse result = new BasicResponse();
        result.status = false;
        result.data = "slide delete fail";

        if (objectId == null) {
            result.data = "slide delete fail (objectId is null)";
            return result;
        }

        if (ObjectId.isValid(objectId) == false) {
            result.data = "slide delete fail (objectId is not valid)";
            return result;
        }

        Workbook workbook = workbookRepository.findById(new ObjectId(objectId));
        if(workbook == null || workbook.getUserId() != userId || workbook.getSlideList().size() <= slideIndex){
            result.data = "slide delete fail";
            return result;
        }

        workbook.getSlideList().remove(slideIndex);
        workbookRepository.save(workbook);

        result.status = true;
        result.data = "slide delete success";
        return result;
    }
}
