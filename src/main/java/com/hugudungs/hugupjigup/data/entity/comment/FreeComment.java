package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.board.FreeText;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.text_comment_type.CommentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "free_comment")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_text_comment_id", nullable = false))
})
public class FreeComment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "comment_type_id", nullable = false)
    private CommentType commentType;  // FK - 댓글 유형 참조

    @ManyToOne
    @JoinColumn(name = "free_text_id", nullable = false)
    private FreeText freeText;  // FK - 자유 게시판 글 참조

    @ManyToOne
    @JoinColumn(name = "free_comment_userId", nullable = false)
    private User user;  // FK - Users 테이블 참조 (작성자)

    @Column(name = "free_comment_text", nullable = false, columnDefinition = "TEXT")
    private String content;  // 댓글 내용

    @Column(name = "free_comment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;  // 댓글 작성 날짜
}