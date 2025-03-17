package com.hugudungs.hugupjigup.Auth.UserInfo.repository;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Free, Long> {

    @Query("SELECT COUNT(f) FROM Free f WHERE f.user = :user")
    int countByUser(User user);
}
