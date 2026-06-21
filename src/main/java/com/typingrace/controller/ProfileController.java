package com.typingrace.controller;

import com.typingrace.domain.entity.Profile;
import com.typingrace.dto.response.ApiResponse;
import com.typingrace.dto.response.ProfileResponse;
import com.typingrace.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProfileResponse>> getProfileById(@PathVariable String id){
        try {
            ProfileResponse existingProfile = profileService.getProfile(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User fetched", existingProfile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
