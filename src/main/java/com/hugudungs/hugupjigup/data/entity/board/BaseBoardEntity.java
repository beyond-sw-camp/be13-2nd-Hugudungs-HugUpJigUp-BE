package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.enums.BoardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

/**
 * 게시판의 최소 엔티티를 포함한 추상 클래스
 */

@Data
@MappedSuperclass
public abstract class BaseBoardEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "board_type", nullable = false)
    private BoardType boardType;

    private String title;

    private String content;

    private int views;
}
