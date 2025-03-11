package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.board.FreeText;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;

import java.util.Date;
import lombok.*;

@Entity
@Table(name = "free_comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_text_comment_id", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_comment_text", nullable = false, columnDefinition = "TEXT"))
})
public class FreeComment extends BaseCommentEntity {

    @ManyToOne
    @JoinColumn(name = "free_text_id", nullable = false)
    private FreeText freeText;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "comment_type_id", nullable = false)
    private CommentTypes commentTypeId;

    @Column(name = "free_comment_date", nullable = false)
    @Temporal(TemporalType.DATE) // 자동으로 '년-월-일'형의 날짜 입력
    private Date freeCommentDate;
}