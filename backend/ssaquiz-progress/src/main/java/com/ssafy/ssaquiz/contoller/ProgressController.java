package com.ssafy.ssaquiz.contoller;

import com.mongodb.ObjectId;
import com.ssafy.ssaquiz.model.Message;
import com.ssafy.ssaquiz.model.MessageType;
import com.ssafy.ssaquiz.service.ProgressService;
import io.swagger.annotations.ApiOperation;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// Controller의 메소드는 message handling methods
// 한 Client에게서 message를 수신한 다음, 다른 Client에게 broadcast
@CrossOrigin(origins = {"*"})
@RestController
public class ProgressController {
    @Autowired
    ProgressService progressService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/room/enterUser/{pin}")
    public void enterUser(@DestinationVariable("pin") int pin, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("enterUser()");
        System.out.println(message);

        if (message.getType() == MessageType.JOIN) {
            if (progressService.registUser("userList" + pin, message.getSender())) {
                message.setContent("닉네임 등록 성공");
            } else {
                message.setContent("닉네임 등록 실패");
            }
        }

        if (message.getType() == MessageType.LEAVE) {

        }

        headerAccessor.getSessionAttributes().put("nickname", message.getSender());
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/startQuiz/{pin}")
    public void startQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("startQuiz()");
        System.out.println(message);

        if (progressService.setAnswerList("answerList" + pin, (ArrayList) message.getContent())) {
            message.setContent("퀴즈 시작 성공");
        } else {
            message.setContent("퀴즈 시작 실패");
        }
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/finishQuiz/{pin}")
    public void finishQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("finishQuiz()");
        System.out.println(message);

        // 1, 2, 3등 닉네임 점수 보여주기
        message.setContent(progressService.viewRanking("userList" + pin, 0, 2));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/nextQuiz/{pin}")
    public void nextQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("nextQuiz()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/endQuiz/{pin}")
    public void endQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("endQuiz()");
        System.out.println(message);

        // 1, 2, 3등 닉네임 점수 보여주기
        message.setContent(progressService.viewRanking("userList" + pin, 0, 2));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/sendCategory/{pin}")
    public void sendCategory(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("sendCategory()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/sendAnswer/{pin}")
    public void sendAnswer(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("sendAnswer()");
        System.out.println(message);
        Object originContent = message.getContent();

        boolean isCorrect = progressService.grade("answerList" + pin, message.getQuizNum(), (String) message.getContent());

        if (isCorrect) {
            double CurrentScore = progressService.plusScore("userList" + pin, message.getSender(),10.0);
            // 정오답 여부, 현재점수를 답을 제출한 사용자에게 알려주는 로직 추가하기
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer", true);
            jsonObject.put("plusScore", 10);
            jsonObject.put("CurrentScore", CurrentScore);
            message.setContent(jsonObject);
            simpMessagingTemplate.convertAndSend("/pin/" + pin + "/nickname/" + message.getSender(), message);
        } else{
            // 정오답 여부, 현재점수를 답을 제출한 사용자에게 알려주는 로직 추가하기
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer", false);
            jsonObject.put("plusScore", 0);
            jsonObject.put("CurrentScore", progressService.getScore("userList" + pin, message.getSender()));
            message.setContent(jsonObject);
            simpMessagingTemplate.convertAndSend("/pin/" + pin + "/nickname/" + message.getSender(), message);
        }

        message.setContent(originContent);
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/sendUserList/{pin}")
    public void sendUserList(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("sendUserList()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @MessageMapping("/room/sendTotalNum/{pin}")
    public void sendTotalNum(@DestinationVariable("pin") int pin, @Payload Message message) {
        System.out.println("sendTotalNum()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @ApiOperation(value = "PIN 생성하기")
    @GetMapping("/pin")
    public Object makePin() {
        return progressService.makePin();
    }

    @ApiOperation(value = "TEST")
    @PostMapping("/test")
    public boolean test(@RequestParam String id) {
        return ObjectId.isValid(id);
    }
}