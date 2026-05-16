package com.typingrace.controller;

import com.typingrace.dto.request.LoginRequest;
import com.typingrace.dto.request.RegisterRequest;
import com.typingrace.dto.response.ApiResponse;
import com.typingrace.dto.response.JwtResponse;
import com.typingrace.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody RegisterRequest registerRequest) {
        try {
            String message = authService.registerUser(registerRequest);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success(message, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.fail(e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse tokenPayload = authService.loginUser(loginRequest);
            return ResponseEntity.ok()
                    .body(ApiResponse.success("Login successful!", tokenPayload));
        } catch (Exception e) {
            // Catches BadCredentialsException or other authentication errors cleanly
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(ApiResponse.fail("Invalid username or password!"));
        }
    }
}