package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="matching")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_id")),
        @AttributeOverride(name = "title", column = @Column(name = "matching_title")),
        @AttributeOverride(name = "content", column = @Column(name = "matching_content")),
        @AttributeOverride(name = "views", column = @Column(name = "matching_views"))
})
public class Matching extends BaseBoardEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String tag; // 태그 (예: 작업, 기술)
}
