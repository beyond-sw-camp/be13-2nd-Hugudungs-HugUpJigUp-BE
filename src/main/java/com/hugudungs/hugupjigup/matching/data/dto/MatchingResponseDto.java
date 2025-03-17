package com.hugudungs.hugupjigup.matching.data.dto;

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
public class MatchingResponseDto {
    private Long matchingId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BoardType boardType;
    private String matchingContent;
    private String matchingTitle;
    private int matchingViews;
    private String tag;
    private Long authorId;
}
