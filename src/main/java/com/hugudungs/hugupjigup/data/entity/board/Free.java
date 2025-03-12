package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "free")
@Getter
@Setter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_views", nullable = false, columnDefinition = "INT DEFAULT 0"))
})
public class Free extends BaseBoardEntity{
    @ManyToOne
    @JoinColumn(name = "board_type_id", nullable = false)
    private BoardTypes boardTypeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}