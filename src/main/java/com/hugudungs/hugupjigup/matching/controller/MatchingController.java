package com.hugudungs.hugupjigup.matching.controller;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MatchingController {
    @PostMapping("/matching/{userId}")
    @Operation(summary = "매칭 게시글 등록 메서드", description = "매칭 게시글 등록 메서드입니다.")
    ResponseEntity<MatchingResponseDto> createMatching(
            @RequestBody MatchingRequestDto requestDto) throws Exception;

    @PutMapping("/update/{matchingId}")
    @Operation(summary = "매칭 게시글 수정 메서드", description = "매칭 게시글 수정 메서드입니다.")
    ResponseEntity<MatchingResponseDto> updateMatching(
            @PathVariable Long matchingId,
            @RequestBody MatchingRequestDto requestDto) throws Exception;

    @DeleteMapping("/delete/{matchingId}")
    @Operation(summary = "매칭 게시글 삭제 메서드", description = "매칭 게시글 삭제 메서드입니다.")
    ResponseEntity<Void> deleteMatching(
            @PathVariable Long matchingId) throws Exception;

    @GetMapping("/posts")
    @Operation(summary = "매칭 게시판 조회 메서드", description = "매칭 게시판 조회 메서드입니다.")
    ResponseEntity<Page<MatchingResponseDto>> getMatchingPosts(
            @ParameterObject Pageable pageable) throws Exception;

    @GetMapping("/{matchingId}")
    @Operation(summary = "매칭 게시글 조회 메서드", description = "매칭 게시글 조회 메서드입니다.")
    ResponseEntity<MatchingResponseDto> getMatchingById(
            @PathVariable Long matchingId) throws Exception;
}