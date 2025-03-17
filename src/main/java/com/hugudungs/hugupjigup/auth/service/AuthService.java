package com.hugudungs.hugupjigup.auth.service;

import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;

public interface AuthService {
    boolean findUserByEmail(String email);

    boolean findUserByNickname(String nickname);

    void sendOtp(String email);

    boolean verifyOtp(VerificationOtpRequestDto verificationOtpRequestDto);

    void createUser(SignUpRequestDto signUpRequestDto);
}
