package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.common.enums.BoardType;

import jakarta.persistence.EnumType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity
@Getter  // ✅ Lombok Getter 추가
@Table(name = "board_types")
@AttributeOverride(name = "id", column = @Column(name = "board_type_id", columnDefinition = "TINYINT"))
public class BoardTypeEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "board_type")
    private BoardType boardType;

    // ✅ `getBoardType()` 메서드 추가
    public BoardType getBoardType() {
        return this.boardType;
    }
}
