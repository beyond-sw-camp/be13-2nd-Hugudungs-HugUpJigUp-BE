package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.common.enums.ApplicationQueueStatus;
import com.hugudungs.hugupjigup.data.entity.matching.ApplicationQueue;
import com.hugudungs.hugupjigup.data.entity.matching.Matching;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueRequestDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueResponseDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueUpdateDto;
import com.hugudungs.hugupjigup.repository.applicationqueue.ApplicationQueueRepository;
import com.hugudungs.hugupjigup.repository.board.MatchingRepository;
import com.hugudungs.hugupjigup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationQueueServiceImpl implements ApplicationQueueService {

    private final ApplicationQueueRepository applicationQueueRepository;
    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ApplicationQueueResponseDto createApplication(Long matchingId, ApplicationQueueRequestDto requestDto) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 매칭이 존재하지 않습니다."));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        ApplicationQueue applicationQueue = new ApplicationQueue(
                matching,
                user,
                requestDto.getContent(),
                ApplicationQueueStatus.WAIT
        );

        ApplicationQueue savedApplication = applicationQueueRepository.save(applicationQueue);
        return ApplicationQueueResponseDto.fromEntity(savedApplication);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationQueueResponseDto> getApplicationsByMatchingId(Long matchingId) {
        return applicationQueueRepository.findByMatchingId(matchingId)
                .stream()
                .map(ApplicationQueueResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApplicationQueueResponseDto> getApplicationsByUserId(Long userId) {
        return applicationQueueRepository.findByUserId(userId)
                .stream()
                .map(ApplicationQueueResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ApplicationQueueResponseDto updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto) {
        ApplicationQueue applicationQueue = applicationQueueRepository.findById(applicationQueueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 존재하지 않습니다."));

        applicationQueue.setContent(requestDto.getContent());
        applicationQueue.setApplicationQueueStatus(requestDto.getApplicationQueueStatus());

        ApplicationQueue updatedApplication = applicationQueueRepository.save(applicationQueue);
        return ApplicationQueueResponseDto.fromEntity(updatedApplication);
    }

    @Override
    @Transactional
    public void deleteApplication(Long applicationQueueId) {
        ApplicationQueue applicationQueue = applicationQueueRepository.findById(applicationQueueId)
                .orElseThrow(() -> new IllegalArgumentException("해당 신청이 존재하지 않습니다."));

        applicationQueueRepository.delete(applicationQueue);
    }
}