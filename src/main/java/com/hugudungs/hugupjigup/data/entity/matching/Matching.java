package com.hugudungs.hugupjigup.data.entity.matching;

import com.hugudungs.hugupjigup.data.entity.board.BaseBoardEntity;
import com.hugudungs.hugupjigup.data.entity.user.User;

import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(nullable = false)
    private String tag;
}
