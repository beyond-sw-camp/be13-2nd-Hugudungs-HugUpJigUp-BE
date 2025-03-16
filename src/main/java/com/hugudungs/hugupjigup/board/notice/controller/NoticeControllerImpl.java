package com.hugudungs.hugupjigup.board.notice.controller;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import com.hugudungs.hugupjigup.board.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공지 게시판 API", description = "공지 게시판 API")
@RestController
@RequestMapping("/api/v1/notice")
public class NoticeControllerImpl implements NoticeController {
    private final NoticeService noticeService;

    public NoticeControllerImpl(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Operation(summary = "test 메서드", description = "test 메서드입니다.")
    @GetMapping(value = "/test")
    public String getHello(){
        return "Hello World";
    }

    @Override
    @PostMapping("/create/{userId}")
    public ResponseEntity<NoticeResponseDto> createProject(
            @PathVariable Long userId,
            @RequestBody NoticeRequestDto requestDto) {
        NoticeResponseDto responseDto = noticeService.createNotice(userId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}