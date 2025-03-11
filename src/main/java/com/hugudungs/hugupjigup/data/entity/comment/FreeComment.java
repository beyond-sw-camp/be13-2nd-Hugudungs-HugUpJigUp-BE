package com.hugudungs.hugupjigup.data.entity.comment;

import com.hugudungs.hugupjigup.data.entity.board.FreeText;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import lombok.*;
import com.hugudungs.hugupjigup.data.entity.type.CommentType;

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

//    현재 User 엔티티가 구현 되어있지 않으므로 일단 주석 처리했음. 구현 되면 주석을 제거하면 됨.
//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private Integer userId;

    @ManyToOne
    @JoinColumn(name = "comment_type_id", nullable = false)
    private CommentType commentType;

    @Column(name = "free_comment_date", nullable = false)
    @Temporal(TemporalType.DATE) // 자동으로 '년-월-일'형의 날짜 입력
    private Date freeCommentDate;
}