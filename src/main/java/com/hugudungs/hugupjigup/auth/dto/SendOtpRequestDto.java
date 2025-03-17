package com.hugudungs.hugupjigup.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SendOtpRequestDto {
    final String email;
}
