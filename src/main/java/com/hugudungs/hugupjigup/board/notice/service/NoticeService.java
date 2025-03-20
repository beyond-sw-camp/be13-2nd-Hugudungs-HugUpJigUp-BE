package com.hugudungs.hugupjigup.board.notice.service;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    NoticeResponseDto createNotice(NoticeRequestDto requestDto) throws Exception;

    NoticeResponseDto updateNotice(Long noticeId, NoticeRequestDto requestDto) throws Exception;

    void deleteNotice(Long id) throws Exception;

    Page<NoticeResponseDto> getNoticePosts(Pageable pageable) throws Exception;

    NoticeResponseDto getNoticeById(Long noticeId) throws Exception;
}
