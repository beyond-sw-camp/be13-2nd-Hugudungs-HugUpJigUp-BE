package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.data.entity.comment.CommentTypes;
import com.hugudungs.hugupjigup.data.entity.comment.FreeComment;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentGenerationResponseDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateDto;
import com.hugudungs.hugupjigup.dto.comment.freecomment.FreeCommentUpdateResponseDto;
import com.hugudungs.hugupjigup.repository.comment.CommentTypesRepository;
import com.hugudungs.hugupjigup.repository.comment.FreeCommentRepository;
import com.hugudungs.hugupjigup.repository.board.FreeRepository;
import com.hugudungs.hugupjigup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreeCommentServiceImpl implements FreeCommentService {

    private final FreeCommentRepository freeCommentRepository;
    private final UserRepository userRepository;
    private final FreeRepository freeRepository;
    private final CommentTypesRepository commentTypesRepository;

    @Override
    @Transactional
    public FreeCommentGenerationResponseDto createComment(FreeCommentGenerationDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Free free = freeRepository.findById(requestDto.getFreeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        CommentTypes commentType = commentTypesRepository.findById(requestDto.getCommentTypeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 타입이 존재하지 않습니다."));

        FreeComment freeComment = new FreeComment();
        freeComment.setContent(requestDto.getContent());
        freeComment.setUser(user);
        freeComment.setFree(free);
        freeComment.setCommentTypeEntity(commentType);

        freeCommentRepository.save(freeComment);

        return fromEntity(freeComment);
    }

    private FreeCommentGenerationResponseDto fromEntity(FreeComment freeComment) {
        return new FreeCommentGenerationResponseDto(
                freeComment.getId(),
                freeComment.getContent(),
                freeComment.getUser().getNickName(),
                freeComment.getCommentTypeEntity().getCommentType().toString()
        );
    }

    @Override
    @Transactional
    public FreeCommentUpdateResponseDto updateComment(Long commentId, FreeCommentUpdateDto requestDto) {
        FreeComment freeComment = freeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        freeComment.setContent(requestDto.getContent());

        FreeComment updatedComment = freeCommentRepository.save(freeComment);
        return mapToDto(updatedComment);
    }

    private FreeCommentUpdateResponseDto mapToDto(FreeComment freeComment) {
        return new FreeCommentUpdateResponseDto(
                freeComment.getId(),
                freeComment.getContent(),
                freeComment.getUser().getId(),
                freeComment.getFree().getId()
        );
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        FreeComment freeComment = freeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        freeCommentRepository.delete(freeComment);
    }
}