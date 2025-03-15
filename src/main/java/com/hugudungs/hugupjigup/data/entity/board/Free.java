package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.user.User;

import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Table(name = "free")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_views", nullable = false, columnDefinition = "INT DEFAULT 0"))
})
public class Free extends BaseBoardEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardTypeEntity boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}