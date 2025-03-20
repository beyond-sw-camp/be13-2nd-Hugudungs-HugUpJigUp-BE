package com.hugudungs.hugupjigup.board.notice.controller;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import com.hugudungs.hugupjigup.board.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지 게시판 API", description = "공지 게시판 API")
@RestController
@Slf4j
@RequestMapping("/api/v1/notice")
public class NoticeControllerImpl implements NoticeController {
    private final NoticeService noticeService;

    public NoticeControllerImpl(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<NoticeResponseDto> createNotice(
            @RequestBody NoticeRequestDto requestDto) {
        try {
            log.info("Received request: {}", requestDto); // 요청 데이터 로깅
            NoticeResponseDto responseDto = noticeService.createNotice(requestDto);
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (DataAccessException e) {
            log.error("Database error: ", e); // 상세 오류 로깅
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            log.error("Unexpected error: ", e); // 상세 오류 로깅
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @PutMapping("/update/{noticeId}")
    public ResponseEntity<NoticeResponseDto> updateNotice(
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto) {
        try {
            NoticeResponseDto responseDto = noticeService.updateNotice(noticeId, requestDto);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<Void> deleteNotice(
            @PathVariable Long noticeId) {
        try {
            noticeService.deleteNotice(noticeId);

            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (UnauthorizedException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/get/{noticeId}")
    public ResponseEntity<NoticeResponseDto> getNotice(
            @PathVariable Long noticeId) {
        try {
            NoticeResponseDto responseDto = noticeService.getNoticeById(noticeId);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping("/posts")
    public ResponseEntity<Page<NoticeResponseDto>> getNoticePosts(
            @ParameterObject Pageable pageable) {
        try {
            Page<NoticeResponseDto> responseDto = noticeService.getNoticePosts(pageable);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}