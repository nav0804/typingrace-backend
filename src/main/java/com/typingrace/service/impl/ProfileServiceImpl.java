package com.typingrace.service.impl;

import com.typingrace.domain.entity.Profile;
import com.typingrace.dto.response.ProfileResponse;
import com.typingrace.mapper.ProfileMapper;
import com.typingrace.repository.ProfileRepository;
import com.typingrace.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper){
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }
    @Override
    public ProfileResponse getProfile(String userId) {
        Profile existingProfile = profileRepository.findByUserId(userId);
        if(existingProfile == null){
            throw new EntityNotFoundException("Profile not found");
        }
        return profileMapper.modelToDto(existingProfile);
    }
}
