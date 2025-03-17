package com.hugudungs.hugupjigup.matching.controller;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface MatchingController {
    @Operation(summary = "매칭 게시글 등록 메서드", description = "매칭 게시글 등록 메서드입니다.")
    public ResponseEntity<MatchingResponseDto> createMatching(
            @PathVariable Long userId,
            @RequestBody MatchingRequestDto requestDto);

    @Operation(summary = "매칭 게시글 수정 메서드", description = "매칭 게시글 수정 메서드입니다.")
    public ResponseEntity<MatchingResponseDto> updateMatching(
            @PathVariable Long matchingId,
            @RequestBody MatchingRequestDto requestDto) throws Exception;

    @Operation(summary = "매칭 게시글 삭제 메서드", description = "매칭 게시글 삭제 메서드입니다.")
    public ResponseEntity<MatchingResponseDto> deleteMatching(
            @PathVariable Long matchingId) throws Exception;

    @Operation(summary = "매칭 게시판 조회 메서드", description = "매칭 게시판 조회 메서드입니다.")
    public ResponseEntity<Page<MatchingResponseDto>> getMatching(Pageable pageable) throws Exception;
}
