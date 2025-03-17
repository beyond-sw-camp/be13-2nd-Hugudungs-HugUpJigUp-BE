package com.hugudungs.hugupjigup.board.notice.controller;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NoticeController {
    @Operation(summary = "공지 게시글 등록 메서드", description = "공지 게시글 등록 메서드입니다.")
    public ResponseEntity<NoticeResponseDto> createNotice(
            @PathVariable Long userId,
            @RequestBody NoticeRequestDto requestDto) throws Exception;

    @Operation(summary = "공지 게시글 수정 메서드", description = "공지 게시글 수정 메서드입니다.")
    public ResponseEntity<NoticeResponseDto> updateNotice(
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto) throws Exception;

    @Operation(summary = "공지 게시글 삭제 메서드", description = "공지 게시글 삭제 메서드입니다.")
    public ResponseEntity<Void> deleteNotice(
            @PathVariable Long noticeId) throws Exception;

    @Operation(summary = "공지 게시글 조회 메서드", description = "공지 게시글 조회 메서드입니다.")
    public ResponseEntity<NoticeResponseDto> getNotice(
            @PathVariable Long noticeId) throws Exception;

    @Operation(summary = "공지 게시판 조회 메서드", description = "공지 게시판 조회 메서드입니다.")
    public ResponseEntity<Page<NoticeResponseDto>> getNoticePosts(
            @PathVariable Pageable pageable) throws Exception;
}