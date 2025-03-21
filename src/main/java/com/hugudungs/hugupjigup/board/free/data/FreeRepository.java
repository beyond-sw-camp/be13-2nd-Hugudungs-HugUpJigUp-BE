package com.hugudungs.hugupjigup.board.free.data;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FreeRepository extends JpaRepository<Free, Long> {

    @Query("SELECT f FROM Free f LEFT JOIN FETCH f.freeComments WHERE f.id = :id")
    Optional<Free> findByIdWithComments(@Param("id") Long id);

    // 제목으로 검색
    Page<Free> findByTitleContaining(String titleKeyword, Pageable pageable);

    // 내용으로 검색
    Page<Free> findByContentContaining(String contentKeyword, Pageable pageable);

    // 제목 + 내용 검색
    Page<Free> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);
}

