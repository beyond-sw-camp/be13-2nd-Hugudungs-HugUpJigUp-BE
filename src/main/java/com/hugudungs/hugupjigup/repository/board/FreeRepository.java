package com.hugudungs.hugupjigup.repository.board;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;  // ✅ import 추가!

public interface FreeRepository extends JpaRepository<Free, Long> {
    // 📦 검색 기능 추가 (제목 또는 내용에 검색어 포함)
    Page<Free> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);
}

