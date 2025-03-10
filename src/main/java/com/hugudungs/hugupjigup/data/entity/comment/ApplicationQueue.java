package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="application_queue")
public class ApplicationQueue extends BaseEntity {
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Enum status; // 대기열 상태를 뜻함 (Ex. 대기중, 승낙, 멘토링 완료)
}
