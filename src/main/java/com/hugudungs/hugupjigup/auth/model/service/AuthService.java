package com.hugudungs.hugupjigup.auth.model.service;

import com.hugudungs.hugupjigup.auth.model.dto.TokenResponseDto;

public interface AuthService {
    TokenResponseDto login(String email, String password);

    void logout(String bearerToken);

    TokenResponseDto refresh(String bearerToken);
}
