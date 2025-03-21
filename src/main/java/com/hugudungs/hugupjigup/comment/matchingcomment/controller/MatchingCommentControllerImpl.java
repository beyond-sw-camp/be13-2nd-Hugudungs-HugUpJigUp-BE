package com.hugudungs.hugupjigup.comment.matchingcomment.controller;

import com.hugudungs.hugupjigup.comment.matchingcomment.data.dto.MatchingCommentRequestDto;
import com.hugudungs.hugupjigup.comment.matchingcomment.data.dto.MatchingCommentResponseDto;
import com.hugudungs.hugupjigup.comment.matchingcomment.data.dto.MatchingCommentUpdateDto;
import com.hugudungs.hugupjigup.comment.matchingcomment.service.MatchingCommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchingCommentControllerImpl implements MatchingCommentController {

    private final MatchingCommentService matchingCommentService;

    @Override
    public ResponseEntity<Void> createComment(Long matchingId, MatchingCommentRequestDto requestDto) {
        matchingCommentService.createMatchingComment(matchingId, requestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateComment(Long matchingId, Long commentId, MatchingCommentUpdateDto requestDto) {
        matchingCommentService.updateMatchingComment(matchingId, commentId, requestDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<MatchingCommentResponseDto>> getCommentsByMatchingId(Long matchingId) {
        return ResponseEntity.ok(matchingCommentService.getCommentsByMatchingId(matchingId));
    }

    @Override
    public ResponseEntity<Void> deleteComment(Long matchingId, Long commentId) {
        matchingCommentService.deleteMatchingComment(matchingId, commentId);
        return ResponseEntity.noContent().build();
    }
}
