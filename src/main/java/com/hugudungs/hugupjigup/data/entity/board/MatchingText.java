package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.text_comment_type.TextType;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "matching_text")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_text_id")),
        @AttributeOverride(name = "title", column = @Column(name = "matching_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "matching_text", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "matching_number", columnDefinition = "INT DEFAULT 0"))
})
public class MatchingText extends BaseBoardEntity {

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;  // FK - Users 테이블 참조

    @OneToOne
    @JoinColumn(name = "text_type_id", nullable = false)
    private TextType textType;  // FK - TextType 테이블 참조

    @Column(name = "matching_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;  // 작성 날짜

    @Column(name = "tag", nullable = false)
    private String tag;  // 태그 (직업, 기술)
}
