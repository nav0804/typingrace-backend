package com.typingrace.event;


import com.typingrace.service.RedisService;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
public class WebSocketEventListener {

    private final RedisService redisService;

    public WebSocketEventListener(RedisService redisService) {
        this.redisService = redisService;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal user = accessor.getUser();
        if (user != null) {
            redisService.userConnected(user.getName());
            System.out.println("User connected: " + user.getName());
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal user = accessor.getUser();
        if (user != null) {
            redisService.userDisconnected(user.getName());
            System.out.println("User disconnected: " + user.getName());
        }
    }
}