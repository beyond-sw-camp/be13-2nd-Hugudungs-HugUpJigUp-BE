package com.hugudungs.hugupjigup.comment.freecomment.data;

import com.hugudungs.hugupjigup.data.entity.comment.FreeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreeCommentRepository extends JpaRepository<FreeComment, Long> {
    List<FreeComment> findByFreeId(Long freeId);
}
