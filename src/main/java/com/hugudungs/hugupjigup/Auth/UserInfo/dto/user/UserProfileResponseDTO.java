package com.hugudungs.hugupjigup.auth.userInfo.dto.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDTO {
    private String name;
    private String currentJob;
    private String desiredJob;
    private int commentCount;
    private int matchingCommentCount;
    private int matchingCount;
    private int postCount;
    private String mentorIntroduction; // 멘토 자기소개
    private String menteeIntroduction; // 멘티 자기소개
}


