package com.hugudungs.hugupjigup.user.data;

import com.hugudungs.hugupjigup.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
