package com.typingrace.service;

import com.typingrace.dto.request.LoginRequest;
import com.typingrace.dto.request.RegisterRequest;
import com.typingrace.dto.response.JwtResponse;

public interface AuthService {
    String registerUser(RegisterRequest registerRequest);
    JwtResponse loginUser(LoginRequest loginRequest);
}