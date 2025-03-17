package com.hugudungs.hugupjigup.auth.controller;

import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;
import com.hugudungs.hugupjigup.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Authentication API", description = "사용자 인증 관련 API")
public interface AuthController {
    ResponseEntity<ResponseDto<Void>> emailDuplicateCheck(@RequestParam("email") String email);

    ResponseEntity<ResponseDto<Void>> nicknameDuplicateCheck(@RequestParam("nickname") String nickname);

    ResponseEntity<ResponseDto<Void>> sendOtp(@RequestBody String email);

    ResponseEntity<ResponseDto<Void>> verificationOtp(@RequestBody VerificationOtpRequestDto verificationOtpRequestDto);

    ResponseEntity<ResponseDto<Void>> signUp(@RequestBody SignUpRequestDto signUpRequestDto);
}


