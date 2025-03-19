package com.hugudungs.hugupjigup.controller;

import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateResponseDto;
import com.hugudungs.hugupjigup.service.FreeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FreeCommentControllerImpl implements FreeCommentController {

    private final FreeCommentService freeCommentService;

    @Override
    public ResponseEntity<FreeCommentGenerationResponseDto> createComment(Long freeId, FreeCommentGenerationDto requestDto) {
        requestDto.setFreeId(freeId);
        FreeCommentGenerationResponseDto createdComment = freeCommentService.createComment(requestDto);
        return ResponseEntity.ok(createdComment);
    }

    @Override
    public ResponseEntity<FreeCommentUpdateResponseDto> updateComment(Long freeId, Long commentId, FreeCommentUpdateDto requestDto) {
        requestDto.setFreeId(freeId);
        FreeCommentUpdateResponseDto updatedComment = freeCommentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(updatedComment);
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long commentId) {
        freeCommentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}