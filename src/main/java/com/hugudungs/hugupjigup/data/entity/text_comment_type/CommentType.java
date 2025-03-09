package com.hugudungs.hugupjigup.data.entity.text_comment_type;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment_type")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "comment_type_id", nullable = false))
})
public class CommentType extends BaseEntity {

    @Column(name = "comment_type", nullable = false)
    private String type;  // 댓글 유형
}