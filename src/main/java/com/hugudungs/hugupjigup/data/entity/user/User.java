package com.hugudungs.hugupjigup.data.entity.user;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "text_type_id", nullable = false))
})
public class User extends BaseEntity {

    @Column(name = "`Key`", nullable = false, length = 255)
    private String key;  // 고유 키

    @Column(name = "nickName", nullable = false)
    private String nickName;  // 닉네임

    @Column(name = "email", nullable = false)
    private String email;  // 이메일

    @Column(name = "password", nullable = false)
    private String password;  // 비밀번호

    @Column(name = "deleted_at")
    @Temporal(TemporalType.DATE)
    private Date deletedAt;  // 삭제 날짜 (NULL 허용)

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type")
    private LoginType loginType;  // 로그인 타입 (ENUM) (NULL 허용)

    public enum LoginType {
        GOOGLE, FACEBOOK, KAKAO, LOCAL
    }
}
