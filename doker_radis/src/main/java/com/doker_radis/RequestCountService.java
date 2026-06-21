package com.doker_radis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RequestCountService {

    private final StringRedisTemplate redisTemplate;

    public RequestCountService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Long incrementCount(String method, String uri) {
        String key = buildKey(method, uri);

        /*
         * Redis INCR operation is atomic.
         * Good for multiple Spring Boot instances.
         */
        return redisTemplate.opsForValue().increment(key);
    }

    public String getCount(String method, String uri) {
        String key = buildKey(method, uri);
        String count = redisTemplate.opsForValue().get(key);
        return count == null ? "0" : count;
    }

    public Map<String, String> getAllCounts() {
        Set<String> keys = redisTemplate.keys("request-count:*");

        Map<String, String> result = new LinkedHashMap<>();

        if (keys == null || keys.isEmpty()) {
            return result;
        }

        for (String key : keys) {
            result.put(key, redisTemplate.opsForValue().get(key));
        }

        return result;
    }

    public void resetCount(String method, String uri) {
        String key = buildKey(method, uri);
        redisTemplate.delete(key);
    }

    public void resetAllCounts() {
        Set<String> keys = redisTemplate.keys("request-count:*");

        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }

    private String buildKey(String method, String uri) {
        return "request-count:" + method + ":" + uri;
    }
}