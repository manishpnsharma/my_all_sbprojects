package com.doker_radis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    public RedisController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/save")
    public String save(@RequestParam String key, @RequestParam String value) {
        redisTemplate.opsForValue().set(key, value);
        return "Saved in Redis";
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @PostMapping("/increment")
    public Long increment(@RequestParam String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    @PostMapping("/count")
    public Long countRequest(@RequestParam String url) {
        String key = "request_count:" + url;
        return redisTemplate.opsForValue().increment(key);
    }
}