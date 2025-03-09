package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.board.MatchingText;
import com.hugudungs.hugupjigup.data.entity.text_comment_type.CommentType;
import com.hugudungs.hugupjigup.data.entity.apply.ApplyList;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "matching_comment")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_text_comment_id", nullable = false))
})
public class MatchingComment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "comment_type_id", nullable = false)
    private CommentType commentType;  // FK - 댓글 유형 참조

    @ManyToOne
    @JoinColumn(name = "matching_text_id", nullable = false)
    private MatchingText matchingText;  // FK - MatchingText 테이블 참조

    @ManyToOne
    @JoinColumn(name = "matching_comment_userId", nullable = false)
    private User user;  // FK - Users 테이블 참조 (작성자)

    @OneToOne
    @JoinColumn(name = "apply_list_id", nullable = false)
    private ApplyList applyList;  // FK - ApplyList 테이블 참조

    @Column(name = "matching_comment_text", nullable = false, columnDefinition = "TEXT")
    private String content;  // 후기 내용

    @Column(name = "matching_comment_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;  // 후기 작성 날짜

    @Column(name = "rate", nullable = false)
    private float rate;  // 평점
}
