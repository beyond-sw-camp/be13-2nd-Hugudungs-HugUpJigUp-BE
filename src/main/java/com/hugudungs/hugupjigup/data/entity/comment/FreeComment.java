package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;

import java.util.Date;
import lombok.*;

@Entity
@Table(name = "free_comment")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_comment_id", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_comment_content", nullable = false, columnDefinition = "TEXT"))
})
public class FreeComment extends BaseCommentEntity {

    @ManyToOne
    @JoinColumn(name = "free_id", nullable = false)
    private Free free;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "comment_type_id", nullable = false)
    private CommentTypes commentTypeId;
}