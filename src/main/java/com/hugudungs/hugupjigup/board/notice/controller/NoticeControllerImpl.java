package com.hugudungs.hugupjigup.board.notice.controller;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import com.hugudungs.hugupjigup.board.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
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
@RequestMapping("/api/v1/notice")
public class NoticeControllerImpl implements NoticeController {
    private final NoticeService noticeService;

    public NoticeControllerImpl(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
    
    @Override
    @PostMapping("/create/{userId}")
    public ResponseEntity<NoticeResponseDto> createProject(
            @PathVariable Long userId,
            @RequestBody NoticeRequestDto requestDto) {
        NoticeResponseDto responseDto = noticeService.createNotice(userId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    
    @Override
    @PutMapping("/update/{noticeId}")
    public ResponseEntity<NoticeResponseDto> updateProject(
            @PathVariable Long noticeId,
            @RequestBody NoticeRequestDto requestDto) throws Exception {
        NoticeResponseDto responseDto = noticeService.updateNotice(noticeId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Override
    @DeleteMapping("/delete/{noticeId}")
    public ResponseEntity<NoticeResponseDto> deleteProject(
            @PathVariable Long noticeId) throws Exception {
        try {
            noticeService.deleteNotice(noticeId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ErrorResponseException e) {
//FIXME: UnauthorizedException 구현        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            //FIXME: 다른 예외 처리 생각
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/get/{noticeId}")
    public ResponseEntity<NoticeResponseDto> getNotice(@PathVariable Long noticeId) throws Exception {
        try {
            NoticeResponseDto responseDto = noticeService.getNoticeById(noticeId);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//            FIXME: 커스텀 에러 구문 } catch (RuntimeException e) {
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping("/{pageable}")
    public ResponseEntity<Page<NoticeResponseDto>> getNoticePosts(Pageable pageable) {
        try {
            Page<NoticeResponseDto> responseDto = noticeService.getNoticePosts(pageable);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//FIXME: 커스텀 에러 구문        } catch (RuntimeException e) {
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}