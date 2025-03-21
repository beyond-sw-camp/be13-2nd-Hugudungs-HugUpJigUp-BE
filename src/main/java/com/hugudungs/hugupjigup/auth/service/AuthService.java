package com.hugudungs.hugupjigup.auth.service;

import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.TokenResponseDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;

public interface AuthService {
    boolean hasUserByEmail(String email);

    boolean hasUserByNickname(String nickname);

    void sendOtp(String email);

    boolean checkOtpValidity(VerificationOtpRequestDto verificationOtpRequestDto);

    void createUser(SignUpRequestDto signUpRequestDto);

    TokenResponseDto login(String email, String password);

    void logout(String bearerToken);

    TokenResponseDto refresh(String bearerToken);
}
