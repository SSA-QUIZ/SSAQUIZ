package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.model.Message;
import com.ssafy.ssaquiz.model.MessageType;
import com.ssafy.ssaquiz.util.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class ProgressService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Value("${prefix.userList}")
    private String USER_LIST;

    @Value("${prefix.answerList}")
    private String ANSWER_LIST;

    @Value("${prefix.time}")
    private String TIME;

    @Value("${prefix.question}")
    private String QUESTION;

    public void enterUser(int pin, Message message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("enterUser()");
        System.out.println(message);

        if (message == null || message.getSender() == null) {
            message.setContent("join fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        if (message.getSender().length() > 15) {
            message.setContent("join fail (over length)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        if (registUser(USER_LIST + pin, message.getSender())) {
            message.setContent("join success");
            headerAccessor.getSessionAttributes().put("nickname", message.getSender());
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        message.setContent("join fail (overlap)");
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void outsideUser(int pin, Message message, SimpMessageHeaderAccessor headerAccessor) {
        System.out.println("outsideUser()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void startQuiz(int pin, Message message) {
        System.out.println("startQuiz()");
        System.out.println(message);

        if (message == null || message.getContent() == null) {
            message.setContent("start fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        if (setAnswerList(ANSWER_LIST + pin, (ArrayList) message.getContent())) {
            message.setContent("start success");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        message.setContent("start fail");
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void finishQuiz(int pin, Message message) {
        System.out.println("finishQuiz()");
        System.out.println(message);

        if (message == null || message.getQuizNum() == null) {
            message.setContent("finish fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        message.setContent(redisUtil.getRanking(QUESTION + message.getQuizNum() + pin, 0, 2));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void nextQuiz(int pin, Message message) {
        System.out.println("nextQuiz()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void endQuiz(int pin, Message message) {
        System.out.println("sendCategory()");
        System.out.println(message);

        message.setContent(viewRanking(USER_LIST + pin, 0, 2));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void sendCategory(int pin, Message message) {
        System.out.println("endQuiz()");
        System.out.println(message);

        redisUtil.setData(TIME + pin, Long.toString(System.currentTimeMillis() / 100));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void sendAnswer(int pin, Message message) {
        System.out.println("sendAnswer()");
        System.out.println(message);

        if (message == null || message.getContent() == null || message.getSender() == null || message.getQuizNum() == null) {
            message.setContent("submit fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        Object originContent = message.getContent();
        boolean isCorrect = grade(ANSWER_LIST + pin, message.getQuizNum(), (String) message.getContent());

        if (isCorrect) {
            long plusScore = 600 - ((System.currentTimeMillis() / 100) - Long.parseLong(redisUtil.getData(TIME + pin)));
            redisUtil.addZdata(QUESTION + message.getQuizNum() + pin, message.getSender(), plusScore);
            double CurrentScore = plusScore(USER_LIST + pin, message.getSender(), plusScore);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("answer", true);
            jsonObject.put("plusScore", plusScore);
            jsonObject.put("CurrentScore", CurrentScore);

            message.setContent(jsonObject);
            simpMessagingTemplate.convertAndSend("/pin/" + pin + "/nickname/" + message.getSender(), message);
            message.setContent(originContent);
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("answer", false);
        jsonObject.put("plusScore", 0);
        jsonObject.put("CurrentScore", getScore(USER_LIST + pin, message.getSender()));

        message.setContent(jsonObject);
        simpMessagingTemplate.convertAndSend("/pin/" + pin + "/nickname/" + message.getSender(), message);
        message.setContent(originContent);
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void sendUserList(int pin, Message message) {
        System.out.println("sendUserList()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void sendTotalNum(int pin, Message message) {
        System.out.println("sendTotalNum()");
        System.out.println(message);

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public BasicResponse makePin() {
        // 6자리 PIN 생성 및 중복 검사
        String pin = "";
        String exist = "";
        while (exist != null) {
            pin = RandomStringUtils.randomNumeric(6);
            exist = redisUtil.getData(pin);
        }

        redisUtil.setData(pin, "exist");

        BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "PIN 생성 성공";
        result.object = pin;
        return result;
    }

    public boolean registUser(String key, String nickname) {
        if (key == null || nickname == null) {
            return false;
        }

        return redisUtil.addZdata(key, nickname, 0.0);
    }

    public Set<ZSetOperations.TypedTuple<String>> viewRanking(String key, long startIndex, long endIndex) {
        if (key == null) {
            return null;
        }

        return redisUtil.getRanking(key, startIndex, endIndex);
    }

    public double plusScore(String key, String nickname, double score) {
        if (key == null || nickname == null) {
            return -1.0;
        }

        return redisUtil.plusScore(key, nickname, score);
    }

    public double getScore(String key, Object nickname) {
        if (key == null || nickname == null) {
            return -1.0;
        }

        return redisUtil.getScore(key, nickname);
    }

    public boolean grade(String key, String quizNum, String answer) {
        if (key == null || quizNum == null) {
            return false;
        }

        String rightAnswer = (String) redisUtil.getHdata(key, quizNum);
        if (answer.equals(rightAnswer)) {
            return true;
        }

        return false;
    }

    public boolean setAnswerList(String key, ArrayList answerList) {
        if (key == null || answerList == null) {
            return false;
        }

        for (int i = 0; i < answerList.size(); i++) {
            redisUtil.setHdata(key, Integer.toString(i), answerList.get(i).toString());
        }

        return true;
    }
}
