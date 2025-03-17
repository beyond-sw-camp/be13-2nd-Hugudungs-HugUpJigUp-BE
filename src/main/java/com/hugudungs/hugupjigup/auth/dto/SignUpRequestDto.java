package com.hugudungs.hugupjigup.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {
    private final String email;
    private final String password;
    private final String nickname;
}