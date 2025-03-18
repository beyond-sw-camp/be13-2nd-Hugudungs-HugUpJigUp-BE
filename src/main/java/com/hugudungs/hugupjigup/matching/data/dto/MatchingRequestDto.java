package com.hugudungs.hugupjigup.matching.data.dto;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import lombok.Getter;

@Getter
public class MatchingRequestDto {
    private String title;
    private String content;
    private BoardType boardType;
    private String tag;
}
