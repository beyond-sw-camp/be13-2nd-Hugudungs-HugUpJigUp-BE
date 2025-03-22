package com.hugudungs.hugupjigup.auth.service;

import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.TokenResponseDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;
import com.hugudungs.hugupjigup.auth.exception.UnauthorizeException;
import com.hugudungs.hugupjigup.auth.jwt.JwtTokenProvider;
import com.hugudungs.hugupjigup.auth.userInfo.repository.UserProfileRepository;
import com.hugudungs.hugupjigup.common.cache.CacheService;
import com.hugudungs.hugupjigup.common.email.EmailService;
import com.hugudungs.hugupjigup.common.enums.LoginType;
import com.hugudungs.hugupjigup.common.enums.ProfileType;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import com.hugudungs.hugupjigup.user.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final EmailService emailService;
    private final CacheService cacheService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean hasUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean hasUserByNickname(String nickname) {
        return userRepository.findByNickName(nickname).isPresent();
    }

    @Override
    public void sendOtp(String email) {
        String otpCode = this.createOtp();
        // otp redis에 저장 유효기간 10분
        cacheService.set(this.createOtpKey(email), otpCode, 60 * 10);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("otp", otpCode);
        // 이메일 전송
        emailService.sendMimeMail(email,
                "Hugupjigup 회원가입을 위한 인증번호입니다.",
                parameters, "otp-template");
    }

    @Override
    public boolean checkOtpValidity(VerificationOtpRequestDto verificationOtpRequestDto) {
        String email = verificationOtpRequestDto.getEmail();
        String otpCode = verificationOtpRequestDto.getOtp();
        String originOtp = cacheService.get(this.createOtpKey(email));
        if (originOtp == null || !originOtp.equals(otpCode)) {
            return false;
        }
        cacheService.delete(this.createOtpKey(email));
        // 인증 성공시 verified redis에 저장 유효기간 10분
        cacheService.set(this.verifiedUserKey(email), "1", 60 * 10);

        return true;
    }

    @Override
    @Transactional
    public void createUser(SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        String getVerified = cacheService.get(this.verifiedUserKey(email));

        if (getVerified == null) {
            throw new RuntimeException("인증되지 않은 사용자입니다.");
        }

        userRepository.findByEmail(email).ifPresent(user -> {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        });

        userRepository.findByNickName(signUpRequestDto.getNickname()).ifPresent(user -> {
            throw new RuntimeException("이미 사용중인 닉네임입니다.");
        });

        cacheService.delete(this.verifiedUserKey(email));

        User user = User.builder()
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .nickName(signUpRequestDto.getNickname())
                .loginType(LoginType.COMMON)
                .build();
        userRepository.save(user);

        UserProfile mentorProfile = UserProfile.builder()
                .user(user)
                .profileType(ProfileType.MENTOR)
                .build();
        userProfileRepository.save(mentorProfile);

        UserProfile menteeProfile = UserProfile.builder()
                .user(user)
                .profileType(ProfileType.MENTEE)
                .build();
        userProfileRepository.save(menteeProfile);
    }

    private String createOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            otp.append((int) (Math.random() * 10));
        }

        return otp.toString();
    }

    private String createOtpKey(String email) {
        return "otp:" + email;
    }

    private String verifiedUserKey(String email) {
        return "verified:" + email;
    }

    @Override
    public TokenResponseDto login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizeException("아이디 또는 비밀번호가 올바르지 않습니다."));

        if(user == null || !passwordEncoder.matches(password, user.getPassword())) {
//        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new UnauthorizeException("아이디 또는 비밀번호가 올바르지 않습니다.");
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
            throw new UnauthorizeException("토큰이 유효하지 않습니다.");
        }

        jwtTokenProvider.addBlackList(accessToken);
        jwtTokenProvider.deleteRefreshToken(accessToken);

    }

    @Override
    public TokenResponseDto refresh(String bearerToken) {
        String refreshToken = jwtTokenProvider.resolveToken(bearerToken);

        if (refreshToken == null || !jwtTokenProvider.validateToken(refreshToken)) {
            throw new UnauthorizeException("토큰이 유효하지 않습니다.");
        }

        if (!jwtTokenProvider.isValidRefreshToken(refreshToken)) {
            throw new UnauthorizeException("토큰이 유효하지 않습니다.");
        }

        User user;
        user = userRepository.findByEmail(jwtTokenProvider.getUserEmail(refreshToken))
                .orElseThrow(() -> new UnauthorizeException("아이디 또는 비밀번호가 올바르지 않습니다."));

        return new TokenResponseDto(
                jwtTokenProvider.createAccessToken(user.getUsername(), String.valueOf(user.getRoleType().getRoleType())),
                refreshToken
        );
    }
}
