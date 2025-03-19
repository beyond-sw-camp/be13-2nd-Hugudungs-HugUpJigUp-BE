package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateResponseDto;

public interface FreeCommentService {

    FreeCommentGenerationResponseDto createComment(FreeCommentGenerationDto requestDto);

    FreeCommentUpdateResponseDto updateComment(Long commentId, FreeCommentUpdateDto requestDto);

    void deleteComment(Long commentId);
}