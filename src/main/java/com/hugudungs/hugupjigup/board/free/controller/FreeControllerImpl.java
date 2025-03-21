package com.hugudungs.hugupjigup.board.free.controller;

import com.hugudungs.hugupjigup.board.free.data.dto.FreeCreateRequestDto;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeSearchRequestDto;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeSearchResponseDto;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeUpdateRequestDto;
import com.hugudungs.hugupjigup.board.free.service.FreeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FreeControllerImpl implements FreeController {

    private final FreeService freeService;

    @Override
    public ResponseEntity<FreeSearchResponseDto> createPost(FreeCreateRequestDto requestDto) {
        FreeSearchResponseDto createdPost = freeService.createPost(requestDto);

        return ResponseEntity.ok(createdPost);
    }

    @Override
    public Page<FreeSearchResponseDto> getPosts(FreeSearchRequestDto searchRequest, Pageable pageable) {
        return freeService.getPosts(searchRequest, pageable);
    }

    @Override
    public ResponseEntity<FreeSearchResponseDto> updatePost(Long id, FreeUpdateRequestDto requestDto) {
        FreeSearchResponseDto updatedPost = freeService.updatePost(id, requestDto);

        return ResponseEntity.ok(updatedPost);
    }

    @Override
    public ResponseEntity<String> deletePost(Long id) {
        freeService.deletePost(id);

        return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
    }

    @Override
    public ResponseEntity<FreeSearchResponseDto> getPostById(Long id) {
        FreeSearchResponseDto post = freeService.getPostById(id);

        return ResponseEntity.ok(post);
    }
}