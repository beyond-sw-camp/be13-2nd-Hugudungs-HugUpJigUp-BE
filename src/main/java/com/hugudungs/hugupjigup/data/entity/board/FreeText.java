package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.type.TextType;
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
    @JoinColumn(name = "text_type_id", nullable = false)
    private TextType textType;
//    현재 User 엔티티가 구현 되어있지 않으므로 일단 주석 처리했음. 구현 되면 주석을 제거하면 됨.
//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private Integer userId;

    @Column(name = "free_date", nullable = false)
    @Temporal(TemporalType.DATE) // 자동으로 '년-월-일'형의 날짜 입력
    private Date freeDate;
}