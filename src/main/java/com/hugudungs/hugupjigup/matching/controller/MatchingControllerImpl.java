package com.hugudungs.hugupjigup.matching.controller;

import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import com.hugudungs.hugupjigup.matching.service.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            MatchingRequestDto requestDto) throws Exception  {
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

    @Override
    @DeleteMapping("/delete/{matchingId}")
    public ResponseEntity<MatchingResponseDto> deleteMatching(Long matchingId) throws Exception {
        try {
            matchingService.deleteMatching(matchingId);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ErrorResponseException e) {

//FIXME: UnauthorizedException 구현        } catch (UnauthorizedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {

            //FIXME: 다른 예외 처리 생각
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{pageable}")
    public ResponseEntity<Page<MatchingResponseDto>> getMatching(Pageable pageable) throws Exception {
        try {
            Page<MatchingResponseDto> responseDto = matchingService.getMatchingPosts(pageable);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        } catch (ErrorResponseException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {

            //FIXME: 다른 예외 처리 생각
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @GetMapping("/{matchingId}")
    public ResponseEntity<MatchingResponseDto> getMatchingById(Long matchingId) throws Exception {
        try {
            MatchingResponseDto responseDto = matchingService.getMatchingById(matchingId);

            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//            FIXME: 커스텀 에러 구문 } catch (RuntimeException e) {
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
