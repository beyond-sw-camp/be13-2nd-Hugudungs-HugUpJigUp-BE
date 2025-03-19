package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.dto.board.free.FreeCreateRequestDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeSearchRequestDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeSearchResponseDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreeService {

    FreeSearchResponseDto createPost(FreeCreateRequestDto requestDto);

    Page<FreeSearchResponseDto> getPosts(FreeSearchRequestDto searchRequest, Pageable pageable);

    FreeSearchResponseDto updatePost(Long id, FreeUpdateRequestDto requestDto);

    void deletePost(Long id);

    FreeSearchResponseDto getPostById(Long id);
}