package com.hugudungs.hugupjigup.Auth.UserInfo.repository;

import com.hugudungs.hugupjigup.data.entity.matching.Matching;
import com.hugudungs.hugupjigup.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchingRepository extends JpaRepository<Matching, Long> {

    @Query("SELECT COUNT(m) FROM Matching m WHERE m.user = :user")
    int countByUser(User user);
}
