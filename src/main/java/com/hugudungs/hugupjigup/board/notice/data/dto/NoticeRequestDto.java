package com.hugudungs.hugupjigup.board.notice.data.dto;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import lombok.Getter;

@Getter
public class NoticeRequestDto {
    private String title;
    private String content;
    private BoardType boardType;
}
