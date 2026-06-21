package com.typingrace.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private String id;
    private String name;
    private String dob;
    private String bio;
    private String gender;
    private Integer rank;
    private String profileImageUrl;
    private String userId;
    private String username;
    private String email;
}
