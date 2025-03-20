package com.hugudungs.hugupjigup.dto.comment.freecomment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FreeCommentGenerationResponseDto {

    private Long id;

    private String content;

    private String userNickname;

    private String commentType;
}