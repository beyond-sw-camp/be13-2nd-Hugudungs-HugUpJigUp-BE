package com.hugudungs.hugupjigup.comment.matchingcomment.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MatchingCommentResponseDto {

    private Long commentId;
    private String content;
    private String userNickname;
    private float rate;
    private LocalDateTime createdAt;

    public static MatchingCommentResponseDto fromEntity(com.hugudungs.hugupjigup.data.entity.matching.MatchingComment comment) {
        return new MatchingCommentResponseDto(
                comment.getId(),
                comment.getContent(),
                comment.getUserId().getNickName(),
                comment.getRate(),
                comment.getCreatedAt()
        );
    }
}