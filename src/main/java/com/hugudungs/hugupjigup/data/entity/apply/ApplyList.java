package com.hugudungs.hugupjigup.data.entity.apply;

import com.hugudungs.hugupjigup.data.entity.board.MatchingText;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "apply_list")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "apply_list_id", nullable = false))
})
public class ApplyList extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "matching_text_id", nullable = false)
    private MatchingText matchingText;  // FK - MatchingText 테이블 참조

    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;  // FK - Users 테이블 참조

    @Column(name = "apply_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date applyDate;  // 신청 날짜

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplyStatus status;  // 신청 상태 (ENUM)

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;  // 신청 내용 (NULL 허용)

    public enum ApplyStatus {
        PENDING, APPROVED, REJECTED
    }
}
