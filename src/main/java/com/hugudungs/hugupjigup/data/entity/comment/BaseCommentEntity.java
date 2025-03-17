package com.hugudungs.hugupjigup.data.entity.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.common.enums.CommentType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
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

//    @Enumerated(EnumType.STRING)
//    @Column(name = "comment_type", nullable = false)
//    private CommentType commentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_type_id", nullable = false)
    @JsonIgnore // ✅ Hibernate 프록시 문제 해결
    private CommentTypes commentTypeEntity;

    public CommentType getCommentType() {
        if (this.commentTypeEntity == null) {
            throw new IllegalStateException("🔴 commentTypeEntity가 설정되지 않았습니다.");
        }
        return this.commentTypeEntity.getCommentType();
    }
}
