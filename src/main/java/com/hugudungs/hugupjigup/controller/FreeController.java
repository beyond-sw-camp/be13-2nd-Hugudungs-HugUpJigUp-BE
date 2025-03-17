package com.hugudungs.hugupjigup.controller;

import com.hugudungs.hugupjigup.dto.board.free.FreeGenerationDto;
import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.dto.board.free.FreeInquireDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeUpdateDto;
import com.hugudungs.hugupjigup.service.FreeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/free")
@RequiredArgsConstructor
@Tag(name = "자유게시판 API", description = "자유게시판 관련 API 목록")
public class FreeController {
    private final FreeService freeService;

    // 게시글 생성
    @PostMapping
    @Operation(summary = "자유게시판 게시글 생성", description = "새로운 게시글을 생성합니다.")
    public Free createPost(@RequestBody FreeGenerationDto requestDto) {
        return freeService.createPost(requestDto);
    }

    // 🏛️ 게시글 목록 조회 API (검색, 정렬, 페이징 지원)
    @GetMapping
    @Operation(summary = "자유게시판 게시글 목록 조회", description = "게시글 목록을 조회합니다.")
    public Page<FreeInquireDto> getPosts(
            @RequestParam(defaultValue = "0") int page,  // 현재 페이지 (기본값: 0)
            @RequestParam(defaultValue = "10") int size, // 페이지당 개수 (기본값: 10)
            @RequestParam(required = false) String keyword, // 검색어 (optional)
            @RequestParam(defaultValue = "createdAt") String sortBy, // 정렬 기준
            @RequestParam(defaultValue = "desc") String sortDirection // 정렬 방향
    ) {
        return freeService.getPosts(page, size, keyword, sortBy, sortDirection);
    }

    // ✅ 게시글 수정 (PUT)
    @PutMapping("/{id}")
    @Operation(summary = "자유게시판 게시글 수정", description = "특정 게시글을 수정합니다.")
    public ResponseEntity<Free> updatePost(@PathVariable Long id, @RequestBody FreeUpdateDto requestDto) {
        Free updatedPost = freeService.updatePost(id, requestDto);
        return ResponseEntity.ok(updatedPost);
    }

    // ✅ 게시글 삭제 (DELETE)
    @DeleteMapping("/{id}")
    @Operation(summary = "자유게시판 게시글 삭제", description = "특정 게시글을 삭제합니다.")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        freeService.deletePost(id);
        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    // ✅ 특정 게시글 조회 (GET)
    @GetMapping("/{id}")
    @Operation(summary = "자유게시판 게시글 조회", description = "특정 게시글을 조회합니다.")
    public ResponseEntity<FreeInquireDto> getPostById(@PathVariable Long id) {
        FreeInquireDto post = freeService.getPostById(id);
        return ResponseEntity.ok(post);
    }
}

