package com.hugudungs.hugupjigup.auth.userInfo.service;

import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UserProfileResponseDTO;

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
