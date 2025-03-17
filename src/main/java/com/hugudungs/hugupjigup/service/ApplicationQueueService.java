package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.common.enums.ApplicationQueueStatus;
import com.hugudungs.hugupjigup.data.entity.matching.ApplicationQueue;
import com.hugudungs.hugupjigup.data.entity.matching.Matching;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueUpdateDto;
import com.hugudungs.hugupjigup.repository.applicationqueue.ApplicationQueueRepository;
import com.hugudungs.hugupjigup.repository.user.UserRepository;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueResponseDto;
import com.hugudungs.hugupjigup.repository.board.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationQueueService {

    private final ApplicationQueueRepository applicationQueueRepository;
    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    @Transactional
    public ApplicationQueueResponseDto createApplication(Long matchingId, ApplicationQueueDto requestDto) {
        // ✅ 특정 matchingId에 해당하는 매칭 조회
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 매칭이 존재하지 않습니다."));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // ✅ 신청 엔티티 생성
        ApplicationQueue applicationQueue = new ApplicationQueue(
                matching,
                user,
                requestDto.getContent(),
                ApplicationQueueStatus.WAIT
        );

        // ✅ 저장 후 DTO 변환하여 반환
        ApplicationQueue savedApplication = applicationQueueRepository.save(applicationQueue);
        return ApplicationQueueResponseDto.fromEntity(savedApplication);
    }

    // ✅ 특정 매칭 ID로 신청 목록 조회 (GET)
    @Transactional(readOnly = true)
    public List<ApplicationQueueResponseDto> getApplicationsByMatchingId(Long matchingId) {
        return applicationQueueRepository.findByMatchingId(matchingId)
                .stream()
                .map(ApplicationQueueResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // ✅ 특정 유저 ID로 신청 내역 조회 (GET)
    @Transactional(readOnly = true)
    public List<ApplicationQueueResponseDto> getApplicationsByUserId(Long userId) {
        return applicationQueueRepository.findByUserId(userId)
                .stream()
                .map(ApplicationQueueResponseDto::fromEntity)
                .collect(Collectors.toList());
    }




    // ✅ 멘토링 신청 수정 (PUT)
    @Transactional
    public ApplicationQueueResponseDto updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto) {
        // 1. 신청 내역 조회
        ApplicationQueue applicationQueue = applicationQueueRepository.findById(applicationQueueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 존재하지 않습니다."));

        // 2. 내용 및 상태 업데이트
        applicationQueue.setContent(requestDto.getContent());
        applicationQueue.setApplicationQueueStatus(requestDto.getApplicationQueueStatus());

        // 3. 변경 사항 저장
        ApplicationQueue updatedApplication = applicationQueueRepository.save(applicationQueue);

        // 4. DTO 변환 후 반환
        return ApplicationQueueResponseDto.fromEntity(updatedApplication);
    }




    // ✅ 멘토링 신청 삭제 (DELETE)
    @Transactional
    public void deleteApplication(Long applicationQueueId) {
        // 1. 신청 내역 조회
        ApplicationQueue applicationQueue = applicationQueueRepository.findById(applicationQueueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 존재하지 않습니다."));

        // 2. 삭제 처리
        applicationQueueRepository.delete(applicationQueue);
    }
}
