package com.hugudungs.hugupjigup.data.entity.comment;


import com.hugudungs.hugupjigup.data.entity.board.Matching;
import com.hugudungs.hugupjigup.data.entity.user.RoleTypes;
import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matching_comment")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "matching_comment_id")),
})
public class MatchingComment extends BaseCommentEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_id", referencedColumnName = "matching_id")
    private Matching matching;

    @Column(name = "matching_id", insertable = false, updatable = false)
    private Long matchingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_queue_id", referencedColumnName = "application_queue_id")
    private ApplicationQueue applicationQueue;

    @Column(name = "application_queue_id", insertable = false, updatable = false)
    private Long applicationQueueId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column(name = "rate", nullable = false)
    private float rate;
}

