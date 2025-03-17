package com.hugudungs.hugupjigup.matching.controller;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import com.hugudungs.hugupjigup.matching.service.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "매칭 게시판 API", description = "매칭 게시판 API")
@RestController
@RequestMapping("/api/v1/matching")
public class MatchingControllerImpl implements MatchingController {
    private final MatchingService matchingService;

    public MatchingControllerImpl(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @Override
    @PostMapping("/create/{userId}")
    public ResponseEntity<MatchingResponseDto> createMatching(
            Long userId,
            MatchingRequestDto requestDto) {
        MatchingResponseDto responseDto = matchingService.createMatching(userId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Override
    @PutMapping("/update/{matchingId}")
    public ResponseEntity<MatchingResponseDto> updateMatching(
            Long matchingId,
            MatchingRequestDto requestDto) throws Exception {
        MatchingResponseDto responseDto = matchingService.updateMatching(matchingId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
