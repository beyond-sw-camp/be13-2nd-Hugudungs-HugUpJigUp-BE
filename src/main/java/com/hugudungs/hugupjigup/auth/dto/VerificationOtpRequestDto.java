package com.hugudungs.hugupjigup.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class VerificationOtpRequestDto {
    private final String email;
    private final String otp;
}
