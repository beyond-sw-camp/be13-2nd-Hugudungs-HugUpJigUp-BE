package com.hugudungs.hugupjigup.data.entity.matching;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.common.enums.ApplicationQueueStatus;
import com.hugudungs.hugupjigup.data.entity.user.User;

import jakarta.persistence.FetchType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="application_queue")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "application_queue_id")),
})
public class ApplicationQueue extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id", referencedColumnName = "matching_id")
    private Matching matching;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private ApplicationQueueStatus applicationQueueStatus;

    // ✅ 추가: 모든 필드를 받는 생성자
    public ApplicationQueue(Matching matching, User user, String content, ApplicationQueueStatus status) {
        this.matching = matching;
        this.user = user;
        this.content = content;
        this.applicationQueueStatus = status;
    }
}