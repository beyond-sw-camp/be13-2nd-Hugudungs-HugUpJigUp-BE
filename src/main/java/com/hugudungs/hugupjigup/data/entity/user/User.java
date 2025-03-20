package com.hugudungs.hugupjigup.data.entity.user;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.common.enums.LoginType;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 제한
@AllArgsConstructor
@SuperBuilder
public class User extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_type_id", referencedColumnName = "role_type_id")
    private RoleTypeEntity roleType;

    @Column(name = "user_nickname", nullable = false, unique = true)
    private String nickName;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_login_type", nullable = false)
    private LoginType loginType;
}
