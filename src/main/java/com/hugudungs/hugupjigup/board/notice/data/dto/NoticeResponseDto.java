package com.hugudungs.hugupjigup.board.notice.data.dto;

import com.hugudungs.hugupjigup.data.entity.board.Notice;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeResponseDto {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Long authorId;
    private int noticeViews;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
