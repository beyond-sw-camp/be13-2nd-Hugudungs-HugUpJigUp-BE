package com.hugudungs.hugupjigup.repository.comment;

import com.hugudungs.hugupjigup.data.entity.comment.CommentTypes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentTypesRepository extends JpaRepository<CommentTypes, Long> {
    Optional<CommentTypes> findByCommentType(String commentType);
}
