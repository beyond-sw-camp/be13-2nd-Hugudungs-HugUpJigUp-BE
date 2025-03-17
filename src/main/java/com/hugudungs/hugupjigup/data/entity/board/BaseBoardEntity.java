package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class BaseBoardEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "board_type", nullable = false)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    private String title;

    private String content;

    @Builder.Default
    @Column(columnDefinition = "integer default 0")
    private int views = 0;
}