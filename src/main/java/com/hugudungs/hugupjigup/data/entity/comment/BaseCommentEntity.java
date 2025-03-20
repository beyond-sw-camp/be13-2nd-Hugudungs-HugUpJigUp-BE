package com.hugudungs.hugupjigup.data.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseCommentEntity extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_type_id", nullable = false)
    @JsonIgnore
    private CommentTypes commentTypeEntity;
}
