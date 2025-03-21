package com.hugudungs.hugupjigup.applicationqueue.controller;

import com.hugudungs.hugupjigup.applicationqueue.data.dto.ApplicationQueueRequestDto;
import com.hugudungs.hugupjigup.applicationqueue.data.dto.ApplicationQueueResponseDto;
import com.hugudungs.hugupjigup.applicationqueue.data.dto.ApplicationQueueUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apply")
@Tag(name = "신청 대기열 API", description = "신청 대기열 관련 API 목록")
public interface ApplicationQueueController {

    @Operation(summary = "멘토링 신청", description = "특정 멘토링 글에 대한 신청을 생성합니다.")
    @PostMapping("/{matchingId}")
    ResponseEntity<ApplicationQueueResponseDto> createApplication(
            @PathVariable Long matchingId,
            @RequestBody ApplicationQueueRequestDto requestDto
    );

    @Operation(summary = "특정 매칭 ID의 신청 목록 조회", description = "특정 매칭에 대한 신청글 목록을 조회합니다.")
    @GetMapping("/matching/{matchingId}")
    ResponseEntity<List<ApplicationQueueResponseDto>> getApplicationsByMatchingId(@PathVariable Long matchingId);

    @Operation(summary = "특정 유저 ID의 신청 내역 조회", description = "특정 유저에 대한 신청글 목록을 조회합니다.")
    @GetMapping("/user/{userId}")
    ResponseEntity<List<ApplicationQueueResponseDto>> getApplicationsByUserId(@PathVariable Long userId);

    @Operation(summary = "멘토링 신청 글 수정", description = "특정 멘토링 신청 글을 수정합니다.")
    @PutMapping("/{applicationQueueId}")
    ResponseEntity<ApplicationQueueResponseDto> updateApplication(
            @PathVariable Long applicationQueueId,
            @RequestBody ApplicationQueueUpdateDto requestDto
    );

    @Operation(summary = "멘토링 신청 글 삭제", description = "특정 멘토링 신청 글을 삭제합니다.")
    @DeleteMapping("/{applicationQueueId}")
    ResponseEntity<Void> deleteApplication(@PathVariable Long applicationQueueId);
}