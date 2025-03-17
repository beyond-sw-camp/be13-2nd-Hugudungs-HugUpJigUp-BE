package com.hugudungs.hugupjigup.auth.controller;

import com.hugudungs.hugupjigup.auth.service.AuthService;
import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;
import com.hugudungs.hugupjigup.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController{
    private final AuthService authService;

    @Override
    public ResponseEntity<ResponseDto<Void>> emailDuplicateCheck(String email) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> nicknameDuplicateCheck(String nickname) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> sendOtp(String email) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> verificationOtp(VerificationOtpRequestDto verificationOtpRequestDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDto<Void>> signUp(SignUpRequestDto signUpRequestDto) {
        return null;
    }
}
