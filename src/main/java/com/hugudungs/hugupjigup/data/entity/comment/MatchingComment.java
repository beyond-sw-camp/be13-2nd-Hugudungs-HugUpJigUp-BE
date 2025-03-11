package com.hugudungs.hugupjigup.data.entity.comment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

