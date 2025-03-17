package com.hugudungs.hugupjigup.data.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.common.enums.BoardType;
import jakarta.persistence.EnumType;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder  // ✅ @SuperBuilder 추가 (상속받는 엔티티도 빌더 적용)
public abstract class BaseBoardEntity extends BaseEntity {

    @JsonIgnore // ✅ Swagger UI에서 직렬화하지 않도록 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardTypeEntity boardType;

    private String title;

    private String content;

    private int views;
}
