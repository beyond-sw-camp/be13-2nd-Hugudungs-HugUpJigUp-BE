package com.hugudungs.hugupjigup.Auth.UserInfo.controller;

import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api-docs/user")
@Tag(name = "유저 프로필 수정", description = "유저 프로필 수정 API")
public class UserProfileUpdateController {

    private final UserService userService;

    public UserProfileUpdateController(UserService userService) {
        this.userService = userService;
    }

    // Put
    // 유저의 기본 정보 수정
    @Operation(summary = "유저 프로필 수정", description = "유저의 프로필 정보를 수정합니다.")
    @PutMapping("/{email}")
    public ResponseEntity<Void> updateUserProfile(@PathVariable String email,
                                                  @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        userService.updateUserProfile(email, updateUserProfileDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @Operation(summary = "유저 멘토 프로필 수정", description = "유저의 멘토 프로필 정보를 수정합니다.")
//    @PutMapping("/users/{email}")

}
