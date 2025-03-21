package com.hugudungs.hugupjigup.board.free.data.dto;

import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.common.enums.BoardType;
import com.hugudungs.hugupjigup.data.entity.board.Free;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FreeSearchResponseDto {
    private Long id;
    private BoardType boardType;
    private String title;
    private String content;
    private String userNickname;
    private int views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<FreeCommentGenerationResponseDto> comments;

    public static FreeSearchResponseDto fromEntity(Free free) {
        return FreeSearchResponseDto.builder()
                .id(free.getId())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getAuthor().getNickName())
                .boardType(free.getBoardType())
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .updatedAt(free.getUpdatedAt())
                .build();
    }

    public static FreeSearchResponseDto fromEntity(
            com.hugudungs.hugupjigup.data.entity.board.Free free,
            List<FreeCommentGenerationResponseDto> commentDtos
    ) {
        return FreeSearchResponseDto.builder()
                .id(free.getId())
                .boardType(free.getBoardType())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getAuthor().getNickName())
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .updatedAt(free.getUpdatedAt())
                .comments(commentDtos)
                .build();
    }
}
