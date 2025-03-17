package com.hugudungs.hugupjigup.matching.service;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatchingService {
    MatchingResponseDto createMatching(Long userId, MatchingRequestDto requestDto);

    MatchingResponseDto updateMatching(Long matchingId, MatchingRequestDto requestDto) throws Exception;

    void deleteMatching(Long matchingId) throws Exception;

    Page<MatchingResponseDto> getMatchingPosts(Pageable pageable);
}
