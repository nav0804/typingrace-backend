package com.typingrace.service;

import com.typingrace.domain.entity.Profile;
import com.typingrace.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile(String userId);
}
