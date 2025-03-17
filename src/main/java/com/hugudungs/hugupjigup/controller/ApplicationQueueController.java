package com.hugudungs.hugupjigup.controller;

import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueResponseDto;
import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueUpdateDto;
import com.hugudungs.hugupjigup.service.ApplicationQueueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apply")
@RequiredArgsConstructor
@Tag(name = "신청 대기열 API", description = "신청 대기열 관련 API 목록")
public class ApplicationQueueController {

    private final ApplicationQueueService applicationQueueService;

    // ✅ 멘토링 신청 (POST) - 특정 matchingId 포함
    @PostMapping("/{matchingId}")
    @Operation(summary = "멘토링 신청", description = "특정 멘토링 글에 대한 신청을 생성합니다.")
    public ResponseEntity<ApplicationQueueResponseDto> createApplication(
            @PathVariable Long matchingId,
            @RequestBody ApplicationQueueDto requestDto) {

        // DTO에 matchingId 설정 (선택사항)
        requestDto.setMatchingId(matchingId);

        // ✅ matchingId와 requestDto를 함께 전달하도록 변경
        return ResponseEntity.ok(applicationQueueService.createApplication(matchingId, requestDto));
    }

    // ✅ 특정 매칭 ID의 신청 목록 조회 (GET)
    @GetMapping("/matching/{matchingId}")
    @Operation(summary = "특정 매칭 ID의 신청 목록 조회", description = "특정 매칭에 대한 신청글 목록을 조회합니다.")
    public ResponseEntity<List<ApplicationQueueResponseDto>> getApplicationsByMatchingId(
            @PathVariable Long matchingId) {
        return ResponseEntity.ok(applicationQueueService.getApplicationsByMatchingId(matchingId));
    }

    // ✅ 특정 유저 ID의 신청 내역 조회 (GET)
    @GetMapping("/user/{userId}")
    @Operation(summary = "특정 유저 ID의 신청 내역 조회", description = "특정 유저에 대한 신청글 목록을 조회합니다.")
    public ResponseEntity<List<ApplicationQueueResponseDto>> getApplicationsByUserId(
            @PathVariable Long userId) {
        return ResponseEntity.ok(applicationQueueService.getApplicationsByUserId(userId));
    }




    // ✅ 멘토링 신청 수정 (PUT)
    @PutMapping("/{applicationQueueId}")
    @Operation(summary = "멘토링 신청 글 수정", description = "특정 멘토링 신청 글을 수정합니다.")
    public ResponseEntity<ApplicationQueueResponseDto> updateApplication(
            @PathVariable Long applicationQueueId,
            @Valid @RequestBody ApplicationQueueUpdateDto requestDto) {

        ApplicationQueueResponseDto updatedApplication = applicationQueueService.updateApplication(applicationQueueId, requestDto);
        return ResponseEntity.ok(updatedApplication);
    }




    // ✅ 멘토링 신청 삭제 (DELETE)
    @DeleteMapping("/{applicationQueueId}")
    @Operation(summary = "멘토링 신청 글 삭제", description = "특정 멘토링 신청 글을 삭제합니다.")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationQueueId) {
        applicationQueueService.deleteApplication(applicationQueueId);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
