package com.ssafy.ssaquiz.util;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String getData(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setData(String key, String value) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void setDataExpire(String key, String value, long duration) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void deleteData(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * redis Hash 구조 저장 및 조회를 위한 함수 3개
     */
    // O (1)
    public void setHdata(String redisKey, String key, String value) {
        HashOperations<String, Object, Object> valueOperations = stringRedisTemplate.opsForHash();
        valueOperations.put(redisKey, key, value);
    }

    public Map<Object, Object> getAllHdata(String redisKey) {
        HashOperations<String, Object, Object> valueOperations = stringRedisTemplate.opsForHash();
        return valueOperations.entries(redisKey);
    }

    public Object getHdata(String redisKey, String key) {
        HashOperations<String, Object, Object> valueOperations = stringRedisTemplate.opsForHash();
        return valueOperations.get(redisKey, key);
    }

    /**
     * redis List 구조 저장 및 조회를 위한 함수
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Long setLdata(String key, String value) {
        ListOperations<String, String> valueOperations = stringRedisTemplate.opsForList();
        return valueOperations.rightPush(key, value);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<String> getAllLdata(String key) {
        ListOperations<String, String> valueOperations = stringRedisTemplate.opsForList();
        return valueOperations.range(key, 0, getLSize(key));
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Long getLSize(String key) {
        ListOperations<String, String> valueOperations = stringRedisTemplate.opsForList();
        return valueOperations.size(key);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public List<String> getLastLdata(String key) {
        ListOperations<String, String> valueOperations = stringRedisTemplate.opsForList();
        if (getLSize(key) == 0) {
            return null;
        } else {
            return valueOperations.range(key, -1, -1);
        }
    }

    // O (log (N)) N은 정렬 된 집합의 요소 수
    public boolean addZdata(String key, String value, double score) {
        ZSetOperations<String, String> valueOperations = stringRedisTemplate.opsForZSet();
        return valueOperations.add(key, value, score);
    }

    // O (log (N) + M) N은 정렬 된 집합의 요소 수, M은 반환 된 요소 수
    public Set<ZSetOperations.TypedTuple<String>> getRanking(String key, long startIndex, long endIndex) {
        ZSetOperations<String, String> valueOperations = stringRedisTemplate.opsForZSet();
        return valueOperations.reverseRangeWithScores(key, startIndex, endIndex);
    }

    public Double getScore(String key, Object nickname) {
        ZSetOperations<String, String> valueOperations = stringRedisTemplate.opsForZSet();
        return valueOperations.score(key, nickname);
    }

    public Double plusScore(String key, String nickname, double score) {
        ZSetOperations<String, String> valueOperations = stringRedisTemplate.opsForZSet();
        return valueOperations.incrementScore(key, nickname, score);
    }

    public Long deleteZdata(String key, long startIndex, long endIndex) {
        ZSetOperations<String, String> valueOperations = stringRedisTemplate.opsForZSet();
        return valueOperations.removeRange(key, startIndex, endIndex);
    }
}