package com.hugudungs.hugupjigup.userInfo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UpdateUserMenteeProfileDTO {
    private String desiredJob;
    private String introduction;
    private String experience;
}
