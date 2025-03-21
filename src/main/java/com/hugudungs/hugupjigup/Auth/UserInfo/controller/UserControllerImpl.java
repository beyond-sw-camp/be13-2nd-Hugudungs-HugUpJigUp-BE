package com.hugudungs.hugupjigup.auth.userInfo.controller;

import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.auth.userInfo.dto.user.UserProfileResponseDTO;
import com.hugudungs.hugupjigup.auth.userInfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-docs/user")
@Tag(name = "유저 프로필", description = "유저 프로필 API")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Operation(summary = "유저 프로필 조회", description = "유저의 프로필을 조회합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN - 해당 유저 정보를 찾을 수 없음",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{email}")
    public UserProfileResponseDTO getUserProfile(@PathVariable String email) {
        return userService.getUserProfileByEmail(email);
    }

    // 유저의 기본 정보 수정
    @Override
    @Operation(summary = "유저 프로필 수정", description = "유저의 프로필 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - 프로필 수정 성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST - 잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN - 해당 유저 정보를 찾을 수 없음",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PutMapping("/{email}")
    public UpdateUserProfileDTO updateUserProfile(@PathVariable String email,
                                                  @RequestBody UpdateUserProfileDTO updateUserProfileDTO) {
        return userService.updateUserProfile(email, updateUserProfileDTO);
    }

    // 유저 멘토 정보 수정
    @Override
    @Operation(summary = "유저 멘토 프로필 수정", description = "유저의 멘토 프로필 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - 멘토 프로필 수정 성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST - 잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )

    })
    @PutMapping("{email}/mentor")
    public UpdateUserMentorProfileDTO updateUserMentorProfile(@PathVariable String email,
                                                              @RequestBody UpdateUserMentorProfileDTO updateUserMentorProfileDTO) {
        return userService.updateUserMentorProfile(email, updateUserMentorProfileDTO);
    }

    // 유저 멘티 정보 수정
    @Override
    @Operation(summary = "유저 멘티 프로필 수정", description = "유저의 멘티 프로필 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "OK - 멘티 프로필 수정 성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST - 잘못된 요청 데이터",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "UNAUTHORIZED - 인증되지 않은 사용자",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "FORBIDDEN - 해당 유저 정보를 찾을 수 없음",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR - 서버 오류 발생",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PutMapping("{email}/mentee")
    public UpdateUserMenteeProfileDTO updateUserMenteeProfile(@PathVariable String email,
                                                              @RequestBody UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO) {
        return userService.updateUserMenteeProfile(email, updateUserMenteeProfileDTO);
    }

}
