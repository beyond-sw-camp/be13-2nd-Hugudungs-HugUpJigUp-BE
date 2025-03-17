package com.hugudungs.hugupjigup.auth.dto;

import lombok.Getter;

@Getter
public class VerificationOtpRequestDto {
    private String email;
    private String otp;
}
