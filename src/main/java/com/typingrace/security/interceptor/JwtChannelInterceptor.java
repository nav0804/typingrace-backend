package com.typingrace.security.interceptor;

import com.typingrace.util.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtChannelInterceptor implements ChannelInterceptor {

    private JwtUtils jwtUtils;

    public JwtChannelInterceptor(){
        this.jwtUtils = new JwtUtils();
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel){
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if(accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())){
            String authToken = accessor.getFirstNativeHeader("Authorization");

            if(authToken != null && authToken.startsWith("Bearer")){
                String token = authToken.substring(7);

                String username = jwtUtils.getUsername(token);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, null);
                accessor.setUser(auth);
            }
        }
        return message;
    }
}
