package com.hugudungs.hugupjigup.auth.controller;

import com.hugudungs.hugupjigup.auth.dto.SendOtpRequestDto;
import com.hugudungs.hugupjigup.auth.service.AuthService;
import com.hugudungs.hugupjigup.auth.dto.SignUpRequestDto;
import com.hugudungs.hugupjigup.auth.dto.VerificationOtpRequestDto;
import com.hugudungs.hugupjigup.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    @GetMapping(value = "/duplicate", params = "email")
    @Override
    public ResponseEntity<ResponseDto<Void>> emailDuplicateCheck(@RequestParam String email) {
        // true if email is already in use, false otherwise
        boolean result = authService.hasUserByEmail(email);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Email Duplicate Check Success",
                        result,
                        null
                )
        );
    }

    @GetMapping(value = "/duplicate", params = "nickname")
    @Override
    public ResponseEntity<ResponseDto<Void>> nicknameDuplicateCheck(@RequestParam String nickname) {
        // true if nickname is already in use, false otherwise
        boolean result = authService.hasUserByNickname(nickname);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "Nickname Duplicate Check Success",
                        result,
                        null
                )
        );
    }

    @PostMapping("/otp/send")
    @Override
    public ResponseEntity<ResponseDto<Void>> sendOtp(@RequestBody SendOtpRequestDto sendOtpRequestDto) {
        authService.sendOtp(sendOtpRequestDto.getEmail());

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "OTP Send Success",
                        true,
                        null
                )
        );
    }

    @PostMapping("/otp/verification")
    @Override
    public ResponseEntity<ResponseDto<Void>> verificationOtp(@RequestBody VerificationOtpRequestDto verificationOtpRequestDto) {
        boolean result = authService.checkOtpValidity(verificationOtpRequestDto);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "OTP Verify Success",
                        result,
                        null
                )
        );
    }

    @PostMapping("/signup")
    @Override
    public ResponseEntity<ResponseDto<Void>> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        authService.createUser(signUpRequestDto);

        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK.value(),
                        "OTP Verify Success",
                        true,
                        null
                )
        );
    }
}
