package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.util.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
public class ProgressService {
    @Autowired
    private RedisUtil redisUtil;

    public BasicResponse makePin() {
        // 6자리 PIN 생성 및 중복 검사
        String pin = "";
        String exist = "";
        while (exist != null) {
            pin = RandomStringUtils.randomNumeric(6);
            exist = redisUtil.getData(pin);
        }

        // redisUtil.setData(pin, "exist");
        redisUtil.setDataExpire(pin, "exist", 10L);

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
        if(answer.equals(rightAnswer)) {
            return true;
        }

        return false;
    }

    public boolean setAnswerList(String key, ArrayList answerList) {
        if (key == null || answerList == null) {
            return false;
        }

        for (int i = 0; i < answerList.size(); i++) {
            System.out.println(answerList.get(i).toString());
            redisUtil.setHdata(key, Integer.toString(i), answerList.get(i).toString());
        }

        return true;
    }
}
