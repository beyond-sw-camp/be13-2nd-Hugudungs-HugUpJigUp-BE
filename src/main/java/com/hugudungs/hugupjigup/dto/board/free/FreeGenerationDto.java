package com.hugudungs.hugupjigup.dto.board.free;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeGenerationDto {
    @NotNull(message = "boardType은 필수 입력값입니다.") // ✅ 필수 값 설정
    private BoardType boardType;

    @NotBlank(message = "title은 필수 입력값입니다.")
    private String title;

    @NotBlank(message = "content는 필수 입력값입니다.")
    private String content;

    @NotNull(message = "userId는 필수 입력값입니다.")
    private Long userId;
}
