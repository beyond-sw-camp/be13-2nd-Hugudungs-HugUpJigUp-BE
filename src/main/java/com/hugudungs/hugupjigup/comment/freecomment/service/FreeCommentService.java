package com.hugudungs.hugupjigup.comment.freecomment.service;

import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentGenerationDto;
import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentUpdateDto;
import com.hugudungs.hugupjigup.comment.freecomment.data.dto.FreeCommentUpdateResponseDto;

public interface FreeCommentService {

    FreeCommentGenerationResponseDto createComment(FreeCommentGenerationDto requestDto);

    FreeCommentUpdateResponseDto updateComment(Long commentId, FreeCommentUpdateDto requestDto);

    void deleteComment(Long commentId);
}