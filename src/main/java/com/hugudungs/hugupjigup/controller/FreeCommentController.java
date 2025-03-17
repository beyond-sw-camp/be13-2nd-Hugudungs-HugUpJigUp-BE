package com.hugudungs.hugupjigup.controller;

import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateResponseDto;
import com.hugudungs.hugupjigup.service.FreeCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/free")
@RequiredArgsConstructor
@Tag(name = "자유게시판 댓글 API", description = "자유게시판 댓글 관련 API 목록")
public class FreeCommentController {

    private final FreeCommentService freeCommentService;

    // ✅ 댓글 생성 API
    @Operation(summary = "댓글 생성", description = "특정 게시글에 댓글을 생성합니다.")
    @PostMapping("/{freeId}/comment")
    public ResponseEntity<FreeCommentGenerationResponseDto> createComment(
            @PathVariable Long freeId,
            @Valid @RequestBody FreeCommentGenerationDto requestDto) {

        // DTO의 freeId를 URL의 freeId로 설정
        requestDto.setFreeId(freeId);

        FreeCommentGenerationResponseDto createdComment = freeCommentService.createComment(requestDto);
        return ResponseEntity.ok(createdComment);
    }


    // ✅ 댓글 수정 API
    @Operation(summary = "댓글 수정", description = "특정 게시글의 특정 댓글을 수정합니다.")
    @PutMapping("/{freeId}/comment/{commentId}")
    public ResponseEntity<FreeCommentUpdateResponseDto> updateComment(
            @PathVariable Long freeId,
            @PathVariable Long commentId,
            @Valid @RequestBody FreeCommentUpdateDto requestDto) {

        // DTO에 freeId 설정 (commentId는 매개변수로 넘기므로 필요 없음)
        requestDto.setFreeId(freeId);

        // ✅ Service 메서드 호출 시 commentId를 따로 전달
        FreeCommentUpdateResponseDto updatedComment = freeCommentService.updateComment(commentId, requestDto);
        return ResponseEntity.ok(updatedComment);
    }



    // ✅ 댓글 삭제 API
    @Operation(summary = "댓글 삭제", description = "특정 게시글의 특정 댓글을 삭제합니다.")
    @DeleteMapping("/{freeId}/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "게시글 ID", required = true) @PathVariable Long freeId,
            @Parameter(description = "댓글 ID", required = true) @PathVariable Long commentId) {

        freeCommentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
