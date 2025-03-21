package com.hugudungs.hugupjigup.auth.userInfo.controller;

import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UserProfileResponseDTO;
import io.swagger.v3.oas.annotations.Operation;

public interface UserController {

    // 유저의 기본 정보 조회
    @Operation(summary = "유저 프로필 조회", description = "유저의 프로필을 조회합니다.")
    UserProfileResponseDTO getUserProfile(String email);

    // 유저의 기본 정보 수정
    @Operation(summary = "유저 프로필 수정", description = "유저의 프로필 정보를 수정합니다.")
    UpdateUserProfileDTO updateUserProfile(String email, UpdateUserProfileDTO updateUserProfileDTO);

    // 유저 멘토 정보 수정
    @Operation(summary = "유저 멘토 프로필 수정", description = "유저의 멘토 프로필 정보를 수정합니다.")
    UpdateUserMentorProfileDTO updateUserMentorProfile(String email, UpdateUserMentorProfileDTO updateUserMentorProfileDTO);

    // 유저 멘티 정보 수정
    @Operation(summary = "유저 멘티 프로필 수정", description = "유저의 멘티 프로필 정보를 수정합니다.")
    UpdateUserMenteeProfileDTO updateUserMenteeProfile(String email, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO);
}
