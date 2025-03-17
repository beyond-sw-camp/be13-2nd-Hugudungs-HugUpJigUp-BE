package com.hugudungs.hugupjigup.dto.board.free;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FreeInquireDto {
    private Long id;  // ✅ `long` → `Long`
    private String boardType;
    private String title;
    private String content;
    private String userNickname;
    private int views;
    private LocalDateTime createdAt;

    // ✅ 명시적으로 public 생성자 추가
    public FreeInquireDto(Long id, String boardType, String title, String content, String userNickname, int views, LocalDateTime createdAt) {
        this.id = id;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.userNickname = userNickname;
        this.views = views;
        this.createdAt = createdAt;
    }

    // 📜 엔티티 → DTO 변환
    public static FreeInquireDto fromEntity(Free free) {
        return FreeInquireDto.builder()
                .id(free.getId())
                .title(free.getTitle())
                .content(free.getContent())
                .userNickname(free.getUser().getNickName())
                .views(free.getViews())
                .createdAt(free.getCreatedAt())
                .build();
    }
}
