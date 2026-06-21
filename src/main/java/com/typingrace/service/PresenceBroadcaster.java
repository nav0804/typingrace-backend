package com.typingrace.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class PresenceBroadcaster {

    private final RedisService redisService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PresenceBroadcaster(RedisService redisService, KafkaTemplate<String, String> kafkaTemplate) {
        this.redisService = redisService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void broadcastOnlineCount(){
        Long count = redisService.getOnlineCount();
        System.out.println("Broadcasting count to Kafka: " + count);
        kafkaTemplate.send("presence", String.valueOf(count));
    }
}
