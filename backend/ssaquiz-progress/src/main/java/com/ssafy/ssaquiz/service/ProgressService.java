package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.model.Message;
import com.ssafy.ssaquiz.model.MessageType;
import com.ssafy.ssaquiz.util.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

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

    @Value("${prefix.multiplyList}")
    private String MULTIPLY_LIST;

    @Value("${prefix.answerCnt}")
    private String ANSWER_CNT;

    @Value("${prefix.time}")
    private String TIME;

    @Value("${prefix.question}")
    private String QUESTION;

    private static final Logger logger = LoggerFactory.getLogger(ProgressService.class);

    public void enterTeacher(int pin, Message message, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("enterTeacher()");
        logger.info(message.toString());

        headerAccessor.getSessionAttributes().put("pin", pin);
        headerAccessor.getSessionAttributes().put("teacher", "true");
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void exitTeacher(int pin, Message message, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("exitTeacher()");
        logger.info(message.toString());

        message.setType(MessageType.LEAVE);
        message.setContent("teacher disconnecting");
        headerAccessor.getSessionAttributes().remove("teacher");
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void enterUser(int pin, Message message, SimpMessageHeaderAccessor headerAccessor) {
        logger.info("enterUser()");
        logger.info(message.toString());

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

        if ("".equals(message.getSender()) || message.getSender().replaceAll(" ", "").length() == 0) {
            message.setContent("join fail (space character)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        if (registUser(USER_LIST + pin, message.getSender())) {
            message.setContent("join success");
            headerAccessor.getSessionAttributes().put("pin", pin);
            headerAccessor.getSessionAttributes().put("student", message.getSender());
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        message.setContent("join fail (overlap)");
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void exitUser(int pin, Message message) {
        logger.info("outsideUser()");
        logger.info(message.toString());

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void startQuiz(int pin, Message message) {
        logger.info("startQuiz()");
        logger.info(message.toString());

        if (message == null || message.getContent() == null) {
            message.setContent("start fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        ArrayList list = (ArrayList) message.getContent();
        ArrayList answerList = (ArrayList) list.get(0);
        ArrayList multiplyList = (ArrayList) list.get(1);

        if (setAnswerList(ANSWER_LIST + pin, answerList) && setMultiplyList(MULTIPLY_LIST + pin, multiplyList)) {
            redisUtil.setData(ANSWER_CNT + pin, Integer.toString(answerList.size()));
            message.setContent("start success");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        message.setContent("start fail");
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void finishQuiz(int pin, Message message) {
        logger.info("finishQuiz()");
        logger.info(message.toString());

        if (message == null || message.getQuizNum() == null) {
            message.setContent("finish fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        message.setContent(redisUtil.getRanking(QUESTION + message.getQuizNum() + pin, 0, 2));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void nextQuiz(int pin, Message message) {
        logger.info("nextQuiz()");
        logger.info(message.toString());

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void endQuiz(int pin, Message message) {
        logger.info("sendCategory()");
        logger.info(message.toString());

        message.setContent(viewRanking(USER_LIST + pin, 0, 2));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);

        deleteInRedis(pin);
    }

    @Transactional
    public void deleteInRedis(int pin) {
        String questionCntStr = redisUtil.getData(ANSWER_CNT + pin);
        if(questionCntStr != null) {
            int questionCnt = Integer.parseInt(questionCntStr);
            for (int i = 0; i < questionCnt; i++) {
                redisUtil.deleteZdata(QUESTION + i + pin, 0, -1);
            }
        }

        redisUtil.deleteZdata(USER_LIST + pin, 0, -1);
        redisUtil.deleteData(Integer.toString(pin));
    }

    public void sendCategory(int pin, Message message) {
        logger.info("endQuiz()");
        logger.info(message.toString());

        redisUtil.setData(TIME + pin, Long.toString(System.currentTimeMillis() / 100));
        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    @Transactional
    public void sendAnswer(int pin, Message message) {
        logger.info("sendAnswer()");
        logger.info(message.toString());

        if (message == null || message.getContent() == null || message.getSender() == null || message.getQuizNum() == null) {
            message.setContent("submit fail (null)");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        Object originContent = message.getContent();
        boolean isCorrect = grade(ANSWER_LIST + pin, message.getQuizNum(), (String) message.getContent());

        if (isCorrect) {
            long score = 600 - ((System.currentTimeMillis() / 100) - Long.parseLong(redisUtil.getData(TIME + pin)));
            double multiplyNum = Double.parseDouble((String) redisUtil.getHdata(MULTIPLY_LIST + pin, message.getQuizNum()));
            long plusScore = Math.round(score * multiplyNum);
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
        logger.info("sendUserList()");
        logger.info(message.toString());

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void sendTotalNum(int pin, Message message) {
        logger.info("sendTotalNum()");
        logger.info(message.toString());

        simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
    }

    public void disconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        int pin = -1;
        if (headerAccessor.getSessionAttributes().get("pin") != null) {
            pin = (int) headerAccessor.getSessionAttributes().get("pin");
        }
        String nickname = "";
        if (headerAccessor.getSessionAttributes().get("student") != null) {
            nickname = (String) headerAccessor.getSessionAttributes().get("student");
        }
        String teacher = "";
        if (headerAccessor.getSessionAttributes().get("teacher") != null) {
            teacher = (String) headerAccessor.getSessionAttributes().get("teacher");
        }

        // student disconnect (teacher exist)
        if (!"".equals(teacher) && teacher != null && pin != -1 && !"".equals(nickname) && nickname != null) {
            logger.info("student disconnected (" + nickname + ")");

            Message message = new Message();
            message.setType(MessageType.LEAVE);
            message.setSender(nickname);
            message.setContent("student disconnected");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);

            redisUtil.deleteZdataMember(USER_LIST + pin, (String) headerAccessor.getSessionAttributes().get("student"));
            return;
        }

        // student disconnect (teacher not exist)
        if ("".equals(teacher) && pin != -1 && !"".equals(nickname) && nickname != null) {
            logger.info("student disconnected (" + nickname + ")");

            Message message = new Message();
            message.setType(MessageType.LEAVE);
            message.setSender(nickname);
            message.setContent("student disconnected");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        // teacher disconnect (quiz end)
        if ("".equals(teacher)) {
            logger.info("teacher disconnected");

            Message message = new Message();
            message.setType(MessageType.LEAVE);
            message.setContent("teacher disconnected");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);
            return;
        }

        // teacher disconnect (quiz ongoing)
        if (pin != -1 && !"".equals(teacher) && teacher != null) {
            logger.info("teacher disconnected");

            Message message = new Message();
            message.setType(MessageType.LEAVE);
            message.setContent("teacher disconnected");
            simpMessagingTemplate.convertAndSend("/pin/" + pin, message);

            headerAccessor.getSessionAttributes().remove("teacher");
            deleteInRedis(pin);
            return;
        }
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
        result.data = "PIN create success";
        result.object = pin;
        return result;
    }

    public BasicResponse findPin(String pin) {
        BasicResponse result = new BasicResponse();
        result.status = false;
        result.data = "PIN find fail";

        if (pin == null) {
            return result;
        }

        String exist = redisUtil.getData(pin);
        if (exist == null) {
            return result;
        }

        result.status = true;
        result.data = "PIN find success";
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

    public boolean setMultiplyList(String key, ArrayList multiplyList) {
        if (key == null || multiplyList == null) {
            return false;
        }

        for (int i = 0; i < multiplyList.size(); i++) {
            redisUtil.setHdata(key, Integer.toString(i), multiplyList.get(i).toString());
        }

        return true;
    }
}
