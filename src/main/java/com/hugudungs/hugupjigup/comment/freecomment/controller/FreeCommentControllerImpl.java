package com.hugudungs.hugupjigup.comment.freecomment.controller;

import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentGenerationDto;
import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentUpdateDto;
import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentUpdateResponseDto;
import com.hugudungs.hugupjigup.comment.freecomment.service.FreeCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FreeCommentControllerImpl implements FreeCommentController {

    private final FreeCommentService freeCommentService;

    public ResponseEntity<FreeCommentGenerationResponseDto> createComment(
            @PathVariable Long freeId, @RequestBody FreeCommentGenerationDto requestDto) {

        FreeCommentGenerationResponseDto createdComment = freeCommentService.createComment(freeId, requestDto);
        return ResponseEntity.ok(createdComment);
    }

    public ResponseEntity<FreeCommentUpdateResponseDto> updateComment(
            @PathVariable Long freeId, @PathVariable Long commentId, @RequestBody FreeCommentUpdateDto requestDto) {

        FreeCommentUpdateResponseDto updatedComment = freeCommentService.updateComment(freeId, commentId, requestDto);
        return ResponseEntity.ok(updatedComment);
    }

    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        freeCommentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
