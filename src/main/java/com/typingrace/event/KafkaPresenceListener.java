package com.typingrace.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaPresenceListener {

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaPresenceListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = "presence", groupId = "typing-race-websocket-group")
    public void listenToPresenceUpdates(String onlineCount) {
        System.out.println("✅ KAFKA LISTENER CAUGHT: " + onlineCount);
        messagingTemplate.convertAndSend("/topic/presence", onlineCount);
    }
}
