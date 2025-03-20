package com.hugudungs.hugupjigup.auth.model.service;

import com.hugudungs.hugupjigup.auth.jwt.JwtTokenProvider;
import com.hugudungs.hugupjigup.common.exception.UniversityException;
import com.hugudungs.hugupjigup.common.exception.message.ExceptionMessage;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.auth.model.dto.TokenResponseDto;
import com.hugudungs.hugupjigup.auth.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public TokenResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UniversityException(ExceptionMessage.INVALID_CREDENTIALS));

        if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
//        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UniversityException(ExceptionMessage.INVALID_CREDENTIALS);
        }

        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(user.getUsername(), String.valueOf(user.getRoleType().getRoleType())),
                jwtTokenProvider.createRefreshToken(user.getUsername())
        );
    }

    @Override
    public void logout(String bearerToken) {
        String accessToken = jwtTokenProvider.resolveToken(bearerToken);

        if (accessToken == null || !jwtTokenProvider.validateToken(accessToken)) {
            throw new UniversityException(ExceptionMessage.ACCESS_TOKEN_INVALID);
        }

        jwtTokenProvider.addBlackList(accessToken);
        jwtTokenProvider.deleteRefreshToken(accessToken);

    }

    @Override
    public TokenResponseDto refresh(String bearerToken) {
        String refreshToken = jwtTokenProvider.resolveToken(bearerToken);

        if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) {
            throw new UniversityException(ExceptionMessage.REFRESH_TOKEN_INVALID);
        }

        if (!jwtTokenProvider.isValidRefreshToken(refreshToken)) {
            throw new UniversityException(ExceptionMessage.REFRESH_TOKEN_INVALID);
        }

        User user;
        user = userRepository.findByEmail(jwtTokenProvider.getUserEmail(refreshToken))
                .orElseThrow(() -> new UniversityException(ExceptionMessage.INVALID_CREDENTIALS));

        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(user.getUsername(), String.valueOf(user.getRoleType().getRoleType())),
                refreshToken
        );
    }
}
