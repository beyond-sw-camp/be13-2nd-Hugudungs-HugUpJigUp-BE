package com.hugudungs.hugupjigup.data.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;

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
@SuperBuilder
public abstract class BaseBoardEntity extends BaseEntity {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardTypeEntity boardType;

    private String title;

    private String content;

    private int views;
}
