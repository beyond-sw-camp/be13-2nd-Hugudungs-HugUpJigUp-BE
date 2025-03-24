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
    private UserProfile mentorProfile;
    private UserProfile menteeProfile;
    private int postCount;
    private int commentCount;
    private int matchingCommentCount;
    private int matchingCount;
}


