package com.ssafy.ssaquiz.contoller;

import com.ssafy.ssaquiz.model.Message;
import com.ssafy.ssaquiz.service.ProgressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*"})
@RestController
public class ProgressController {
    @Autowired
    ProgressService progressService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/room/enterTeacher/{pin}")
    public void enterTeacher(@DestinationVariable("pin") int pin, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        progressService.enterTeacher(pin, message, headerAccessor);
    }

    @MessageMapping("/room/exitTeacher/{pin}")
    public void exitTeacher(@DestinationVariable("pin") int pin, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        progressService.exitTeacher(pin, message, headerAccessor);
    }

    @MessageMapping("/room/enterUser/{pin}")
    public void enterUser(@DestinationVariable("pin") int pin, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        progressService.enterUser(pin, message, headerAccessor);
    }

    @MessageMapping("/room/exitUser/{pin}")
    public void outsideUser(@DestinationVariable("pin") int pin, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        progressService.exitUser(pin, message);
    }

    @MessageMapping("/room/startQuiz/{pin}")
    public void startQuiz(@DestinationVariable("pin") int pin, @Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        progressService.startQuiz(pin, message);
    }

    @MessageMapping("/room/finishQuiz/{pin}")
    public void finishQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.finishQuiz(pin, message);
    }

    @MessageMapping("/room/nextQuiz/{pin}")
    public void nextQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.nextQuiz(pin, message);
    }

    @MessageMapping("/room/endQuiz/{pin}")
    public void endQuiz(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.endQuiz(pin, message);
    }

    @MessageMapping("/room/sendCategory/{pin}")
    public void sendCategory(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.sendCategory(pin, message);
    }

    @MessageMapping("/room/sendAnswer/{pin}")
    public void sendAnswer(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.sendAnswer(pin, message);
    }

    @MessageMapping("/room/sendUserList/{pin}")
    public void sendUserList(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.sendUserList(pin, message);
    }

    @MessageMapping("/room/sendTotalNum/{pin}")
    public void sendTotalNum(@DestinationVariable("pin") int pin, @Payload Message message) {
        progressService.sendTotalNum(pin, message);
    }

    @ApiOperation(value = "PIN 생성하기")
    @PostMapping("/pin")
    public Object makePin() {
        return progressService.makePin();
    }

    @ApiOperation(value = "PIN 조회하기")
    @GetMapping("/pin/{pin}")
    public Object findPin(@PathVariable String pin) {
        return progressService.findPin(pin);
    }
}