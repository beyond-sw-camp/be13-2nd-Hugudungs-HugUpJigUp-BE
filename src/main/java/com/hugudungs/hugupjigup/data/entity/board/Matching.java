package com.hugudungs.hugupjigup.data.entity.board;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="matching")
@AttributeOverrides({
        @AttributeOverride(name = "title", column = @Column(name = "matching_title")),
        @AttributeOverride(name = "content", column = @Column(name = "matching_content"))
})
public class Matching extends BaseBoardEntity {
    @Column(nullable = false)
    private String tag; // 태그 (예: 작업, 기술)
}
