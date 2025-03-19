package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueRequestDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueResponseDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueUpdateDto;

import java.util.List;

public interface ApplicationQueueService {

    ApplicationQueueResponseDto createApplication(Long matchingId, ApplicationQueueRequestDto requestDto);

    List<ApplicationQueueResponseDto> getApplicationsByMatchingId(Long matchingId);

    List<ApplicationQueueResponseDto> getApplicationsByUserId(Long userId);

    ApplicationQueueResponseDto updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto);

    void deleteApplication(Long applicationQueueId);
}