package com.hugudungs.hugupjigup.matching.controller;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import com.hugudungs.hugupjigup.matching.service.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "매칭 게시판 API", description = "매칭 게시판 API")
@RestController
@RequestMapping("/api/v1/matching")
@RequiredArgsConstructor
public class MatchingControllerImpl implements MatchingController {
    private final MatchingService matchingService;

    @Override
    public ResponseEntity<MatchingResponseDto> createMatching(
            @RequestBody MatchingRequestDto requestDto) {
        try {
            MatchingResponseDto responseDto = matchingService.createMatching(requestDto);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<MatchingResponseDto> updateMatching(
            Long matchingId,
            MatchingRequestDto requestDto) {
        try {
            MatchingResponseDto responseDto = matchingService.updateMatching(matchingId, requestDto);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<Void> deleteMatching(Long matchingId) {
        try {
            matchingService.deleteMatching(matchingId);

            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch (UnauthorizedException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Page<MatchingResponseDto>> getMatchingPosts(Pageable pageable) {
        try {
            Page<MatchingResponseDto> responseDto = matchingService.getMatchingPosts(pageable);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<MatchingResponseDto> getMatchingById(Long matchingId) {
        try {
            MatchingResponseDto responseDto = matchingService.getMatchingById(matchingId);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
