package com.hugudungs.hugupjigup.userInfo.dto.user;

import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileResponseDTO {
    private String nickname;
    private String email;
    private int postCount;
    private int commentCount;
    private int matchingCommentCount;
    private int matchingCount;
    private String mentorIntroduction;
    private String menteeIntroduction;
    private UserProfileResponse mentorProfile;
    private UserProfileResponse menteeProfile;
}


