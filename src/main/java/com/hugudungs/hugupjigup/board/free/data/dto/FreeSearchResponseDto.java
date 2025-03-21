package com.hugudungs.hugupjigup.board.free.data.dto;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.data.entity.comment.FreeComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class FreeSearchResponseDto {
    private Long id;
    private BoardType boardType;
    private String title;
    private String content;
    private String userNickname;
    private List<FreeCommentDto> comments;
    private int views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 엔티티 → DTO 변환
    public static FreeSearchResponseDto fromEntity(Free free) {
        List<FreeCommentDto> comments = free.getFreeComments()
                .stream()
                .map(FreeCommentDto::fromEntity)
                .collect(Collectors.toList());

        return FreeSearchResponseDto.builder()
                .id(free.getId())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getAuthor().getNickName())
                .boardType(free.getBoardType())
                .comments(comments)
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .updatedAt(free.getUpdatedAt())
                .build();
    }

    @Getter
    private static class FreeCommentDto {
        private Long id;
        private String content;

        public static FreeCommentDto fromEntity(FreeComment freeComment) {
            return new FreeCommentDto(freeComment.getId(), freeComment.getContent());
        }

        public FreeCommentDto(Long id, String content) {
            this.id = id;
            this.content = content;
        }
    }
}
