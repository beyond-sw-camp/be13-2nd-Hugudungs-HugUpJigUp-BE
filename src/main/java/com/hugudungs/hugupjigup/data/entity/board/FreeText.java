package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "free_text")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_text_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_text", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_number", nullable = false, columnDefinition = "INT DEFAULT 0"))
})
public class FreeText extends BaseBoardEntity{
    @ManyToOne
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardTypes boardTypeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Column(name = "free_date", nullable = false)
    @Temporal(TemporalType.DATE) // 자동으로 '년-월-일'형의 날짜 입력
    private Date freeDate;
}