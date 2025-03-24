package com.hugudungs.hugupjigup.auth.repository;

import com.hugudungs.hugupjigup.common.enums.RoleType;
import com.hugudungs.hugupjigup.data.entity.user.RoleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTypeRepository extends JpaRepository<RoleTypeEntity, Long> {
    Optional<RoleTypeEntity> findByRoleType(RoleType roleType);
}
