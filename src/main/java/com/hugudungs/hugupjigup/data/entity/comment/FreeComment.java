package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.data.entity.user.User;

import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "free_comment")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_comment_id", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_comment_content", nullable = false, columnDefinition = "TEXT"))
})
public class FreeComment extends BaseCommentEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "free_id", nullable = false)
    private Free free;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_type_id", nullable = false)
    private CommentTypes commentTypeId;
}