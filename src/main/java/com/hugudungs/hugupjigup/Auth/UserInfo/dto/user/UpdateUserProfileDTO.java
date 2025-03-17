package com.hugudungs.hugupjigup.Auth.UserInfo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserProfileDTO {
    private String name;
    private String email;
    private String password;  // 비밀번호 수정
    private String currentJob;  // current_job 수정
    private String desiredJob;  // desired_job 수정
}
