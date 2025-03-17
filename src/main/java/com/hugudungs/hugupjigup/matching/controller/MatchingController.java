package com.hugudungs.hugupjigup.matching.controller;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MatchingController {
    @Operation(summary = "매칭 게시글 등록 메서드", description = "매칭 게시글 등록 메서드입니다.")
    public ResponseEntity<MatchingResponseDto> createMatching(
            @PathVariable Long userId,
            @RequestBody MatchingRequestDto requestDto);
}
