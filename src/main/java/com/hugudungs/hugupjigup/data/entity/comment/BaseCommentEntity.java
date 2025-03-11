package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseCommentEntity extends BaseEntity {
    @Column(nullable = false)
    private String content;
}
