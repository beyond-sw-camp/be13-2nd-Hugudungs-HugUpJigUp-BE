package com.hugudungs.hugupjigup.comment.freecomment.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "댓글 수정 요청 DTO")
public class FreeCommentUpdateDto {

    @Schema(description = "게시글 ID", example = "1")
    @NotNull(message = "게시글 ID는 필수 입력값입니다.")
    private Long freeId;

    @Schema(description = "댓글 ID", example = "10")
    @NotNull(message = "댓글 ID는 필수 입력값입니다.")
    private Long commentId;

    @Schema(description = "새로운 댓글 내용", example = "수정된 댓글 내용입니다.")
    @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
    private String content;
}