package com.hugudungs.hugupjigup.board.notice.data.dto;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponseDto {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private BoardType boardType;
    private Long authorId;
    private int noticeViews;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
