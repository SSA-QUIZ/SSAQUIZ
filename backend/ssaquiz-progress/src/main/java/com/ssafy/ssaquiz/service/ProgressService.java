package com.ssafy.ssaquiz.service;

import com.ssafy.ssaquiz.model.BasicResponse;
import com.ssafy.ssaquiz.util.RedisUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Set<String> viewRanking(String key, long startIndex, long endIndex) {
        if (key == null) {
            return null;
        }

        return redisUtil.getRanking(key, startIndex, endIndex);
    }

    public double plusScore(String key, String nickname, String quizNum) {
        if (key == null || nickname == null || quizNum == null) {
            return -1.0;
        }

        System.out.println(redisUtil.getScore(key, nickname));
        // 정답 비교 로직
        redisUtil.plusScore(key, nickname, 100);
        System.out.println(redisUtil.getScore(key, nickname));

        return redisUtil.getScore(key, nickname);
    }

    public boolean setAnswerList(String key, ArrayList answerList) {
        if (key == null || answerList == null) {
            return false;
        }

        for (int i = 1; i <= answerList.size(); i++) {
            redisUtil.setHdata(key, Integer.toString(i), answerList.get(i - 1).toString());
        }

        return true;
    }
}
