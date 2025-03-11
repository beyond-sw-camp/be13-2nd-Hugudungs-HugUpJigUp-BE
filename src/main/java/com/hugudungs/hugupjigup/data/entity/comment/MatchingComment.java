package com.hugudungs.hugupjigup.data.entity.comment;


import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "matching_comment")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_comment_id")),
})
public class MatchingComment extends BaseCommentEntity {
    /*
    평점
        컬럼명 not null
     */
    @Column(name = "rate", nullable = false)
    private float rate;
}

