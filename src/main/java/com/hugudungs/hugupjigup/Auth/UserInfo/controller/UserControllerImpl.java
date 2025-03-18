package com.hugudungs.hugupjigup.Auth.UserInfo.controller;

import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UserProfileResponseDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-docs/user")
@Tag(name = "유저 프로필", description = "유저 프로필 API")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    // 유저의 기본 정보 조회
    @Override
    @Operation(summary = "유저 프로필 조회", description = "유저의 프로필을 조회합니다.")
    @GetMapping("/{email}")
    public UserProfileResponseDTO getUserProfile(@PathVariable String email) {
        return userService.getUserProfileByEmail(email);
    }

    // 유저의 기본 정보 수정
    @Override
    @Operation(summary = "유저 프로필 수정", description = "유저의 프로필 정보를 수정합니다.")
    @PutMapping("/{email}")
    public UpdateUserProfileDTO updateUserProfile(@PathVariable String email,
                                                  @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        return userService.updateUserProfile(email, updateUserProfileDTO);
    }

    // 유저 멘토 정보 수정
    @Override
    @Operation(summary = "유저 멘토 프로필 수정", description = "유저의 멘토 프로필 정보를 수정합니다.")
    @PutMapping("{email}/mentor")
    public UpdateUserMentorProfileDTO updateUserMentorProfile(@PathVariable String email,
                                                              @RequestBody UpdateUserMentorProfileDTO updateUserMentorProfileDTO) {
        return userService.updateUserMentorProfile(email, updateUserMentorProfileDTO);
    }

    // 유저 멘티 정보 수정
    @Override
    @Operation(summary = "유저 멘티 프로필 수정", description = "유저의 멘티 프로필 정보를 수정합니다.")
    @PutMapping("{email}/mentee")
    public UpdateUserMenteeProfileDTO updateUserMenteeProfile(@PathVariable String email,
                                                              @RequestBody UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO) {
        return userService.updateUserMenteeProfile(email, updateUserMenteeProfileDTO);
    }
}
