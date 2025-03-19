package com.hugudungs.hugupjigup.dto.board.free;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class FreeSearchResponseDto {
    private Long id;
    private String boardType;
    private String title;
    private String content;
    private String userNickname;
    private int views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 엔티티 → DTO 변환
    public static FreeSearchResponseDto fromEntity(Free free) {
        return FreeSearchResponseDto.builder()
                .id(free.getId())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getUser().getNickName())
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .updatedAt(free.getUpdatedAt())
                .build();
    }
}
