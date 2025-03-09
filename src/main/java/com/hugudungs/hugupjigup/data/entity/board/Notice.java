package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.text_comment_type.TextType;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "notice")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "notice_text_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "notice_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "notice_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "notice_number", columnDefinition = "INT DEFAULT 0"))
})
public class Notice extends BaseBoardEntity {
    @OneToOne
    @JoinColumn(name = "notice_text_userId", nullable = false)
    private User user;  // FK - Users 테이블 참조

    @OneToOne
    @JoinColumn(name = "text_type_id", nullable = false)
    private TextType textType;  // FK - TextType 테이블 참조

    @Column(name = "notice_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;  // 작성 날짜
}
