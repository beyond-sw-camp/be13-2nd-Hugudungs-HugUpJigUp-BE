package com.hugudungs.hugupjigup.board.notice.controller;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import com.hugudungs.hugupjigup.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface NoticeController {
    @Operation(summary = "공지 게시글 등록 메서드", description = "공지 게시글 등록 메서드입니다.")
    ResponseEntity<ResponseDto<NoticeResponseDto>> createNotice(
            @RequestBody NoticeRequestDto requestDto) throws Exception;

    @Operation(summary = "공지 게시글 수정 메서드", description = "공지 게시글 수정 메서드입니다.")
    ResponseEntity<ResponseDto<NoticeResponseDto>> updateNotice(
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto) throws Exception;

    @Operation(summary = "공지 게시글 삭제 메서드", description = "공지 게시글 삭제 메서드입니다.")
    ResponseEntity<ResponseDto<Void>> deleteNotice(
            @PathVariable Long noticeId) throws Exception;

    @Operation(summary = "공지 게시글 조회 메서드", description = "공지 게시글 조회 메서드입니다.")
    ResponseEntity<ResponseDto<NoticeResponseDto>> getNotice(
            @PathVariable Long noticeId) throws Exception;

    @Operation(summary = "공지 게시판 조회 메서드", description = "공지 게시판 조회 메서드입니다.")
    ResponseEntity<ResponseDto<Page<NoticeResponseDto>>> getNoticePosts(
            @ParameterObject Pageable pageable) throws Exception;
}