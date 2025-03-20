package com.hugudungs.hugupjigup.board.notice.data;

import com.hugudungs.hugupjigup.data.entity.board.Notice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<Notice, Long> {
}