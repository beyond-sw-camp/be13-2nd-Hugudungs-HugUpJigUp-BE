package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 게시판의 최소 엔티티를 포함한 추상 클래스
 */

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseBoardEntity extends BaseEntity {
    /**
     * User ID, 게시글 타입은 추후 매핑 추가 과정에서 추가할 예정
     */
    private String title;

    private String content;

    private int views;
}
