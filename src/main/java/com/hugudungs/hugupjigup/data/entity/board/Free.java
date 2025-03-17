package com.hugudungs.hugupjigup.data.entity.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "free")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder  // ✅ 부모 클래스(BaseBoardEntity)의 빌더 패턴 적용
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "free_id", nullable = false)),
        @AttributeOverride(name = "title", column = @Column(name = "free_title", nullable = false)),
        @AttributeOverride(name = "content", column = @Column(name = "free_content", nullable = false, columnDefinition = "TEXT")),
        @AttributeOverride(name = "views", column = @Column(name = "free_views", nullable = false, columnDefinition = "INT DEFAULT 0"))
})
public class Free extends BaseBoardEntity{

    @JsonIgnore // ✅ JSON 변환 시 제외
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}