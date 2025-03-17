package com.hugudungs.hugupjigup.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.common.enums.RoleType;

import jakarta.persistence.EnumType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // ✅ 추가
@Table(name = "role_types")
@AttributeOverride(name = "id", column = @Column(name = "role_type_id", columnDefinition = "TINYINT"))
public class RoleTypeEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;
}