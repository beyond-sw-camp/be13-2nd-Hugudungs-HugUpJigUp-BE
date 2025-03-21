package com.hugudungs.hugupjigup.comment.matchingcomment.service;

import com.hugudungs.hugupjigup.comment.matchingcomment.data.MatchingCommentRepositoryCommentFolder;
import com.hugudungs.hugupjigup.comment.matchingcomment.data.dto.MatchingCommentRequestDto;
import com.hugudungs.hugupjigup.comment.matchingcomment.data.dto.MatchingCommentResponseDto;
import com.hugudungs.hugupjigup.comment.matchingcomment.data.dto.MatchingCommentUpdateDto;
import com.hugudungs.hugupjigup.common.enums.CommentType;
import com.hugudungs.hugupjigup.data.entity.matching.ApplicationQueue;
import com.hugudungs.hugupjigup.data.entity.matching.Matching;
import com.hugudungs.hugupjigup.data.entity.matching.MatchingComment;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.matching.data.MatchingRepository;

import com.hugudungs.hugupjigup.user.data.UserRepository;
import com.hugudungs.hugupjigup.applicationqueue.data.ApplicationQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingCommentServiceImpl implements MatchingCommentService {

    private final MatchingRepository matchingRepository;
    private final MatchingCommentRepositoryCommentFolder matchingCommentRepositoryCommentFolder;
    private final UserRepository userRepository;
    private final ApplicationQueueRepository applicationQueueRepository;

    @Override
    @Transactional
    public void createMatchingComment(Long matchingId, MatchingCommentRequestDto requestDto) {
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new IllegalArgumentException("해당 매칭이 존재하지 않습니다."));

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        ApplicationQueue queue = applicationQueueRepository.findById(requestDto.getApplicationQueueId())
                .orElseThrow(() -> new IllegalArgumentException("해당 신청 대기열이 존재하지 않습니다."));

        MatchingComment comment = new MatchingComment();
        comment.setMatchingId(matching);
        comment.setApplicationQueueId(queue);
        comment.setUserId(user);
        comment.setContent(requestDto.getContent());
        comment.setRate(requestDto.getRate());
        comment.setCommentType(CommentType.MATCHINGCOMMENT);

        matchingCommentRepositoryCommentFolder.save(comment);
    }

    @Override
    @Transactional
    public void updateMatchingComment(Long matchingId, Long commentId, MatchingCommentUpdateDto requestDto) {
        MatchingComment comment = matchingCommentRepositoryCommentFolder.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (comment.getMatchingId() == null || comment.getMatchingId().getId() != matchingId) {
            throw new IllegalArgumentException("해당 매칭에 속한 댓글이 아닙니다.");
        }

        comment.setContent(requestDto.getContent());
        comment.setRate(requestDto.getRate());

    }

    @Override
    @Transactional(readOnly = true)
    public List<MatchingCommentResponseDto> getCommentsByMatchingId(Long matchingId) {
        return matchingCommentRepositoryCommentFolder.findByMatchingId_Id(matchingId)
                .stream()
                .map(MatchingCommentResponseDto::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public void deleteMatchingComment(Long matchingId, Long commentId) {
        MatchingComment comment = matchingCommentRepositoryCommentFolder.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        if (comment.getMatchingId() == null || comment.getMatchingId().getId() != matchingId) {
            throw new IllegalArgumentException("해당 매칭에 속한 댓글이 아닙니다.");
        }

        matchingCommentRepositoryCommentFolder.delete(comment);
    }
}
