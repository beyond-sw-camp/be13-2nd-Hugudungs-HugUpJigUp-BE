package com.hugudungs.hugupjigup.auth.controller;

import com.hugudungs.hugupjigup.auth.dto.SendOtpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;
import com.hugudungs.hugupjigup.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Authentication API", description = "사용자 인증 관련 API")
public interface AuthController {
    @Operation(summary = "이메일 중복 체크", description = "사용자가 입력한 이메일의 중복을 체크합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "401",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<Void>> emailDuplicateCheck(@RequestParam("email") @NotBlank @Email String email);

    @Operation(summary = "닉네임 중복 체크", description = "사용자가 입력한 닉네임의 중복을 체크합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "401",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<Void>> nicknameDuplicateCheck(@RequestParam("nickname") @NotBlank String nickname);


    @Operation(summary = "OTP 코드 전송", description = "사용자가 입력한 이메일로 OTP 코드를 전송합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "401",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<Void>> sendOtp(@RequestBody @Valid SendOtpRequestDto sendOtpRequestDto);

    @Operation(summary = "OTP 코드 검증", description = "사용자가 입력한 OTP 코드를 검증합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "401",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<Void>> verificationOtp(@RequestBody @Valid VerificationOtpRequestDto verificationOtpRequestDto);

    @Operation(summary = "회원가입", description = "사용자의 회원가입을 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(responseCode = "401",
                    description = "성공",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "INTERNAL SERVER ERROR",
                    content = @Content(mediaType = "application/json")
            )
    })
    ResponseEntity<ResponseDto<Void>> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto);
}


