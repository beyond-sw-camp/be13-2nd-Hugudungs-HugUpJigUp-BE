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
    private Long userId;
    private String nickname;
    private String email;
    private String currentJob;
    private String desiredJob;
    private int postCount;
    private int commentCount;
    private int matchingCommentCount;
    private int matchingCount;
    private UserProfileResponse mentorProfile;
    private UserProfileResponse menteeProfile;
}


