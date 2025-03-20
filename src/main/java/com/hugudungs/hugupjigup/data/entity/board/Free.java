package com.hugudungs.hugupjigup.data.entity.board;

import com.hugudungs.hugupjigup.data.entity.comment.FreeComment;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "free")
@Getter
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_views", nullable = false, columnDefinition = "INT DEFAULT 0"))
})


public class Free extends BaseBoardEntity{
    @OneToMany(mappedBy = "free", cascade = CascadeType.REMOVE, orphanRemoval = true) // ✅ 삭제 시 연관 댓글도 삭제
    private List<FreeComment> comments;
}

