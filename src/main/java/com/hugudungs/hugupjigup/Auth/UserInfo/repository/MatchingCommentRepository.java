package com.hugudungs.hugupjigup.Auth.UserInfo.repository;

import com.hugudungs.hugupjigup.data.entity.matching.MatchingComment;
import com.hugudungs.hugupjigup.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatchingCommentRepository extends JpaRepository<MatchingComment, Long> {

    @Query("SELECT COUNT(mc) FROM MatchingComment mc WHERE mc.userId = :user")
    int countByUserComments(User user);
}
