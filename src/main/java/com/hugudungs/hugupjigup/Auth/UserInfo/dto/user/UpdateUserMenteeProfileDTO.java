package com.hugudungs.hugupjigup.Auth.UserInfo.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
public class UpdateUserMenteeProfileDTO {
    private String desiredJob;
    private String introduction;
    private String experience;
}
