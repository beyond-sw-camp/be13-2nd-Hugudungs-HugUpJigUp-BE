package com.hugudungs.hugupjigup.dto.comment.freecomment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "댓글 생성 요청 DTO")
public class FreeCommentGenerationDto {

    @NotNull(message = "게시글 ID는 필수 입력값입니다.")
    private Long freeId;

    @NotNull(message = "유저 ID는 필수 입력값입니다.")
    private Long userId;

    @NotNull(message = "댓글 유형 ID는 필수 입력값입니다.")
    private Long commentTypeId;

    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private String content;
}
