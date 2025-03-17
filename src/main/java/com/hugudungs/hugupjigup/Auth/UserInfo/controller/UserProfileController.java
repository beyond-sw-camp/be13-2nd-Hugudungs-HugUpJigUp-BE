package com.hugudungs.hugupjigup.Auth.UserInfo.controller;

import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UserProfileResponseDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-docs/user")
@Tag(name = "유저 프로필 조회", description = "유저 프로필 조회 API")
public class UserProfileController {

    private final UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    // Get
    // 유저의 기본 정보 조회
    @Operation(summary = "유저 프로필 조회", description = "유저의 프로필을 조회합니다.")
    @GetMapping("/users/{email}")
    public UserProfileResponseDTO getUserProfile(@PathVariable String email) {
        return userService.getUserProfileByEmail(email);
    }
}
