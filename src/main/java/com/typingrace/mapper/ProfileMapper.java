package com.typingrace.mapper;

import com.typingrace.domain.entity.Profile;
import com.typingrace.dto.response.ProfileResponse;
import org.springframework.stereotype.Component;


@Component
public class ProfileMapper {

    public ProfileResponse modelToDto(Profile existingProfile){
        ProfileResponse res = new ProfileResponse();
        res.setId(existingProfile.getId());
        res.setName(existingProfile.getName());
        res.setUserId(existingProfile.getUser().getId());
        res.setEmail(existingProfile.getUser().getEmail());
        res.setUsername(existingProfile.getUser().getUsername());
        return res;
    }
}
