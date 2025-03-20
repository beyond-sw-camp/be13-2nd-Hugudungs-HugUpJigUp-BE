package com.hugudungs.hugupjigup.Auth.UserInfo.service;

import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UserProfileResponseDTO;

public interface UserService {

    // 유저의 기본 정보 조회
    UserProfileResponseDTO getUserProfileByEmail(String email);

    // 유저의 기본 정보 수정
    UpdateUserProfileDTO updateUserProfile(String email, UpdateUserProfileDTO updateUserProfileDTO);

    // 유저 멘토 정보 수정
    UpdateUserMentorProfileDTO updateUserMentorProfile(String email, UpdateUserMentorProfileDTO updateUserMentorProfileDTO);

    // 유저 멘티 정보 수정
    UpdateUserMenteeProfileDTO updateUserMenteeProfile(String email, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO);
}
