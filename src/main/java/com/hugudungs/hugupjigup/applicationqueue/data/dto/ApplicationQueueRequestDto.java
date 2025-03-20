package com.hugudungs.hugupjigup.applicationqueue.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationQueueRequestDto {

    @Schema(description = "매칭 ID", example = "1")
    @NotNull(message = "매칭 ID는 필수 입력값입니다.")
    private Long matchingId;

    @Schema(description = "유저 ID", example = "2")
    @NotNull(message = "유저 ID는 필수 입력값입니다.")
    private Long userId;

    @Schema(description = "신청 내용", example = "멘토링 신청합니다.")
    @NotBlank(message = "신청 내용을 입력해야 합니다.")
    private String content;
}