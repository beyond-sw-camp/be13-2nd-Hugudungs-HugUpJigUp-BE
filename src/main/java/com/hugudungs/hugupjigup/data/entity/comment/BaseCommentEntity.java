package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.common.enums.CommentType;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseCommentEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_type", nullable = false)
    private CommentType commentType;
}

