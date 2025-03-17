package com.hugudungs.hugupjigup.Auth.UserInfo.repository;

import com.hugudungs.hugupjigup.data.entity.comment.FreeComment;
import com.hugudungs.hugupjigup.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<FreeComment, Long> {

    @Query("SELECT COUNT(fc) FROM FreeComment fc WHERE fc.user = :user")
    int countByUser(User user);
}
