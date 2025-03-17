package com.hugudungs.hugupjigup.board.notice.service;

import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    NoticeResponseDto createNotice(Long userId, NoticeRequestDto requestDto);

    NoticeResponseDto updateNotice(Long noticeId, NoticeRequestDto requestDto) throws Exception;

    void deleteNotice(Long id) throws Exception;

    Page<NoticeResponseDto> getNoticePosts(Pageable pageable);

    NoticeResponseDto getNoticeById(Long noticeId);
}
