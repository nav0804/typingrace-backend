package com.typingrace.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    private static final String ONLINE_USERS_KEY = "presence:online";

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void userConnected(String username) {
        redisTemplate.opsForSet().add(ONLINE_USERS_KEY, username);
        redisTemplate.opsForValue().set("presence:" + username, "online", 30, TimeUnit.SECONDS);
    }

    public void userDisconnected(String username) {
        redisTemplate.opsForSet().remove(ONLINE_USERS_KEY, username);
        redisTemplate.delete("presence:" + username);
    }

    public Long getOnlineCount() {
        return redisTemplate.opsForSet().size(ONLINE_USERS_KEY);
    }
}