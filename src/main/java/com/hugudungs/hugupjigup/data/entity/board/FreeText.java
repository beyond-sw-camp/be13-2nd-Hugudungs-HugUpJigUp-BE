package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.text_comment_type.TextType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "free_text")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_text_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_number", columnDefinition = "INT DEFAULT 0"))
})
public class FreeText extends BaseBoardEntity {

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;  // FK - Users 테이블 참조

    @OneToOne
    @JoinColumn(name = "text_type_id", nullable = false)
    private TextType textType;  // FK - TextType 테이블 참조

    @Column(name = "free_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;  // 작성 날짜
}
