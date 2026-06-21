
package com.typingrace.controller;

import com.typingrace.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test-presence")
public class TestPresenceController {

    private final RedisService redisService;

    public TestPresenceController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getOnlineCount() {
        return ResponseEntity.ok(redisService.getOnlineCount());
    }
}

