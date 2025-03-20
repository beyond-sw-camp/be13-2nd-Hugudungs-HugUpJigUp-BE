package com.hugudungs.hugupjigup.repository.applicationqueue;

import com.hugudungs.hugupjigup.data.entity.matching.ApplicationQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationQueueRepository extends JpaRepository<ApplicationQueue, Long> {
    // 특정 매칭 ID에 대한 신청 목록 조회
    List<ApplicationQueue> findByMatchingId(Long matchingId);

    // 특정 유저 ID가 신청한 내역 조회
    List<ApplicationQueue> findByUserId(Long userId);
}
