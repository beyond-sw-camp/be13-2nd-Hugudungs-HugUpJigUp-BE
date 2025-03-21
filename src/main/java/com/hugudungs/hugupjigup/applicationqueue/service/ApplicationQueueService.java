package com.hugudungs.hugupjigup.applicationqueue.service;

import com.hugudungs.hugupjigup.applicationqueue.data.dto.ApplicationQueueRequestDto;
import com.hugudungs.hugupjigup.applicationqueue.data.dto.ApplicationQueueResponseDto;
import com.hugudungs.hugupjigup.applicationqueue.data.dto.ApplicationQueueUpdateDto;

import java.util.List;

public interface ApplicationQueueService {

    ApplicationQueueResponseDto createApplication(Long matchingId, ApplicationQueueRequestDto requestDto);

    List<ApplicationQueueResponseDto> getApplicationsByMatchingId(Long matchingId);

    List<ApplicationQueueResponseDto> getApplicationsByUserId(Long userId);

    ApplicationQueueResponseDto updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto);

    void deleteApplication(Long applicationQueueId);
}