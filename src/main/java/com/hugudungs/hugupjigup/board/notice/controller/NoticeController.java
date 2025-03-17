package com.hugudungs.hugupjigup.board.notice.controller;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface NoticeController {
    @Operation(summary = "공지 게시글 등록 메서드", description = "공지 게시글 등록 메서드입니다.")
    public ResponseEntity<NoticeResponseDto> createProject(
            @PathVariable Long userId,
            @RequestBody NoticeRequestDto requestDto);

    @Operation(summary = "공지 게시글 수정 메서드", description = "공지 게시글 수정 메서드입니다.")
    public ResponseEntity<NoticeResponseDto> updateProject(
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto) throws Exception;

    @Operation(summary = "공지 게시글 삭제 메서드", description = "공지 게시글 삭제 메서드입니다.")
    public ResponseEntity<NoticeResponseDto> deleteProject(
            @PathVariable Long noticeId) throws Exception;
}