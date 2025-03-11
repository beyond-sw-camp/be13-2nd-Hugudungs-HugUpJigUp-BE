package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "notice")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "notice_id")),
        @AttributeOverride(name = "title", column = @Column(name = "notice_title")),
        @AttributeOverride(name = "content", column = @Column(name = "notice_content"))
})
public class Notice extends BaseBoardEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
}
