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
public class FreeCommentService {

    private final FreeCommentRepository freeCommentRepository;
    private final UserRepository userRepository;
    private final FreeRepository freeRepository;
    private final CommentTypesRepository commentTypesRepository;

    @Transactional
    public FreeCommentGenerationResponseDto createComment(FreeCommentGenerationDto requestDto) {
        // ✅ 1. 유저 조회
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // ✅ 2. 게시글 조회
        Free free = freeRepository.findById(requestDto.getFreeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // ✅ 3. 댓글 타입 조회
        CommentTypes commentType = commentTypesRepository.findById(requestDto.getCommentTypeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 타입이 존재하지 않습니다."));

        // ✅ 4. 댓글 생성 및 저장
        FreeComment freeComment = new FreeComment();
        freeComment.setContent(requestDto.getContent());
        freeComment.setUser(user);
        freeComment.setFree(free);
        freeComment.setCommentTypeEntity(commentType);

        freeCommentRepository.save(freeComment);

        // ✅ 5. Response DTO 반환
        return fromEntity(freeComment);
    }

    public static FreeCommentGenerationResponseDto fromEntity(FreeComment freeComment) {
        return new FreeCommentGenerationResponseDto(
                freeComment.getId(),
                freeComment.getContent(),
                freeComment.getUser().getNickName(),
                freeComment.getCommentTypeEntity().getCommentType().toString()
        );
    }





    @Transactional
    public FreeCommentUpdateResponseDto updateComment(Long commentId, FreeCommentUpdateDto requestDto) {
        // ✅ 1. 댓글 조회
        FreeComment freeComment = freeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // ✅ 2. 댓글 내용 업데이트
        freeComment.setContent(requestDto.getContent());

        // ✅ 3. 변경된 댓글 저장
        FreeComment updatedComment = freeCommentRepository.save(freeComment);

        // ✅ 4. DTO로 변환 후 반환
        return mapToDto(updatedComment);
    }

    private FreeCommentUpdateResponseDto mapToDto(FreeComment freeComment) {
        FreeCommentUpdateResponseDto responseDto = new FreeCommentUpdateResponseDto();
        responseDto.setId(freeComment.getId());
        responseDto.setContent(freeComment.getContent());
        responseDto.setUserId(freeComment.getUser().getId());
        responseDto.setFreeId(freeComment.getFree().getId());
        return responseDto;
    }




    @Transactional
    public void deleteComment(Long commentId) {
        // ✅ 1. 댓글 조회 (존재하지 않으면 예외 발생)
        FreeComment freeComment = freeCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // ✅ 2. 댓글 삭제
        freeCommentRepository.delete(freeComment);
    }
}
