package com.hugudungs.hugupjigup.board.notice.data.dto;

import lombok.*;

@Getter
public class NoticeRequestDto {
    private String title;
    private String content;
    private Long userId;
}
