package com.hugudungs.hugupjigup.controller;

import com.hugudungs.hugupjigup.dto.board.free.FreeCreateRequestDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeSearchRequestDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeSearchResponseDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeUpdateRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/api/v1/free")
@Tag(name = "자유게시판 API", description = "자유게시판 관련 API 목록")
public interface FreeController {

    @PostMapping
    @Operation(summary = "자유게시판 게시글 생성", description = "새로운 게시글을 생성합니다.")
    ResponseEntity<FreeSearchResponseDto> createPost(@RequestBody FreeCreateRequestDto requestDto);

    @GetMapping
    @Operation(summary = "자유게시판 목록 조회", description = "자유 게시판 목록을 조회합니다.")
    Page<FreeSearchResponseDto> getPosts(
            @ParameterObject FreeSearchRequestDto searchRequest,
            @ParameterObject Pageable pageable
    );

    @PutMapping("/{id}")
    @Operation(summary = "자유게시판 게시글 수정", description = "특정 게시글을 수정합니다.")
    ResponseEntity<FreeSearchResponseDto> updatePost(
            @PathVariable Long id,
            @RequestBody FreeUpdateRequestDto requestDto
    );

    @DeleteMapping("/{id}")
    @Operation(summary = "자유게시판 게시글 삭제", description = "특정 게시글을 삭제합니다.")
    ResponseEntity<String> deletePost(@PathVariable Long id);

    @GetMapping("/{id}")
    @Operation(summary = "자유게시판 게시글 조회", description = "특정 게시글을 조회합니다.")
    ResponseEntity<FreeSearchResponseDto> getPostById(@PathVariable Long id);
}