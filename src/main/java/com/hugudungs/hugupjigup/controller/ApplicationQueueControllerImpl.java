//package com.hugudungs.hugupjigup.controller;
//
//import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueRequestDto;
//import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueResponseDto;
//import com.hugudungs.hugupjigup.dto.applicationqueue.ApplicationQueueUpdateDto;
//import com.hugudungs.hugupjigup.service.ApplicationQueueService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class ApplicationQueueControllerImpl implements ApplicationQueueController {
//
//    private final ApplicationQueueService applicationQueueService;
//
//    @Override
//    public ResponseEntity<ApplicationQueueResponseDto> createApplication(Long matchingId, ApplicationQueueRequestDto requestDto) {
//        requestDto.setMatchingId(matchingId);
//        return ResponseEntity.ok(applicationQueueService.createApplication(matchingId, requestDto));
//    }
//
//    @Override
//    public ResponseEntity<List<ApplicationQueueResponseDto>> getApplicationsByMatchingId(Long matchingId) {
//        return ResponseEntity.ok(applicationQueueService.getApplicationsByMatchingId(matchingId));
//    }
//
//    @Override
//    public ResponseEntity<List<ApplicationQueueResponseDto>> getApplicationsByUserId(Long userId) {
//        return ResponseEntity.ok(applicationQueueService.getApplicationsByUserId(userId));
//    }
//
//    @Override
//    public ResponseEntity<ApplicationQueueResponseDto> updateApplication(Long applicationQueueId, ApplicationQueueUpdateDto requestDto) {
//        return ResponseEntity.ok(applicationQueueService.updateApplication(applicationQueueId, requestDto));
//    }
//
//    @Override
//    public ResponseEntity<Void> deleteApplication(Long applicationQueueId) {
//        applicationQueueService.deleteApplication(applicationQueueId);
//        return ResponseEntity.noContent().build();
//    }
//}