package com.hugudungs.hugupjigup.userInfo.dto.user;

import com.hugudungs.hugupjigup.common.enums.ProfileType;
import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserProfileResponse {
    private float rate;
    private ProfileType profileType;
    private String introduction;

    public static UserProfileResponse of(UserProfile userProfile) {
        return UserProfileResponse.builder()
                .rate(userProfile.getRate())
                .profileType(userProfile.getProfileType())
                .introduction(userProfile.getIntroduction())
                .build();
    }
}
