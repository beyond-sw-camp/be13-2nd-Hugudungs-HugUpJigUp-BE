package com.hugudungs.hugupjigup.data.entity.user;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.enums.RoleType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role_types")
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "role_type_id", columnDefinition = "TINYINT"))
public class RoleTypes extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;
}
