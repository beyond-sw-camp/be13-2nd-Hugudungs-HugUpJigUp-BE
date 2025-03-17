package com.hugudungs.hugupjigup.matching.service;

import com.hugudungs.hugupjigup.common.enums.BoardType;
import com.hugudungs.hugupjigup.data.entity.matching.Matching;
import com.hugudungs.hugupjigup.matching.data.MatchingRepository;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingRequestDto;
import com.hugudungs.hugupjigup.matching.data.dto.MatchingResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MatchingServiceImpl implements MatchingService {
    private final MatchingRepository matchingRepository;

    public MatchingServiceImpl(MatchingRepository matchingRepository) {
        this.matchingRepository = matchingRepository;
    }


    @Override
    public MatchingResponseDto createMatching(Long userId, MatchingRequestDto requestDto) {
        //FIXME: userId로 User를 가져오는 로직이 완성되면 추가
        //FIXME: userId로 관리자를 판별하는 로직
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));

        Matching matching = Matching.builder()
                .boardType(BoardType.MATCHING)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .views(0)
                .tag(requestDto.getTag())
//                .user(user)
                .build();

        Matching savedMatching = matchingRepository.save(matching);

        MatchingResponseDto responseDto = MatchingResponseDto.builder()
                .matchingId(savedMatching.getId())
                .boardType(savedMatching.getBoardType())
                .matchingTitle(savedMatching.getTitle())
                .matchingContent(savedMatching.getContent())
                .matchingViews(savedMatching.getViews())
                .authorId(savedMatching.getAuthor().getId())
                .build();

        return responseDto;
    }

    @Override
    public MatchingResponseDto updateMatching(Long matchingId, MatchingRequestDto requestDto) throws Exception {
        Matching post = matchingRepository.findById(matchingId).orElseThrow();

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());
        post.setTag(requestDto.getTag());

        try {
            Matching updatedMatching = matchingRepository.saveAndFlush(post);

            MatchingResponseDto responseDto = MatchingResponseDto.builder()
                    .createdAt(updatedMatching.getCreatedAt())
                    .updatedAt(updatedMatching.getUpdatedAt())
                    .boardType(updatedMatching.getBoardType())
                    .matchingId(updatedMatching.getId())
                    .matchingTitle(updatedMatching.getTitle())
                    .matchingContent(updatedMatching.getContent())
                    .matchingViews(updatedMatching.getViews())
                    .authorId(updatedMatching.getAuthor() != null ? updatedMatching.getAuthor().getId() : null)
                    .build();

            return responseDto;
        } catch (RuntimeException e) {
            throw new Exception("게시글 수정 중 데이터베이스 오류가 발생했습니다.", e);
        }
    }
}
