package com.hugudungs.hugupjigup.board.free.service;

import com.hugudungs.hugupjigup.board.free.data.FreeRepository;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeCreateRequestDto;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeSearchRequestDto;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeSearchResponseDto;
import com.hugudungs.hugupjigup.board.free.data.dto.FreeUpdateRequestDto;
import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.user.data.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FreeServiceImpl implements FreeService {
    private final FreeRepository freeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public FreeSearchResponseDto createPost(FreeCreateRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        Free free = Free.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(user)
                .boardType(requestDto.getBoardType())
                .freeComments(new ArrayList<>())
                .build();

        Free savedFree = freeRepository.save(free);
        return FreeSearchResponseDto.fromEntity(savedFree);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FreeSearchResponseDto> getPosts(FreeSearchRequestDto searchRequest, Pageable pageable) {
        if (searchRequest.getKeyword() != null && !searchRequest.getKeyword().isEmpty()) {
            switch (searchRequest.getSearchType()) {
                case "title":
                    return freeRepository.findByTitleContaining(searchRequest.getKeyword(), pageable)
                            .map(FreeSearchResponseDto::fromEntity);
                case "content":
                    return freeRepository.findByContentContaining(searchRequest.getKeyword(), pageable)
                            .map(FreeSearchResponseDto::fromEntity);
                default:
                    return freeRepository.findByTitleContainingOrContentContaining(
                                    searchRequest.getKeyword(), searchRequest.getKeyword(), pageable)
                            .map(FreeSearchResponseDto::fromEntity);
            }
        } else {
            return freeRepository.findAll(pageable)
                    .map(FreeSearchResponseDto::fromEntity);
        }
    }

    @Override
    @Transactional
    public FreeSearchResponseDto updatePost(Long id, FreeUpdateRequestDto requestDto) {
        Free free = freeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        free.setTitle(requestDto.getTitle());
        free.setContent(requestDto.getContent());

        Free updatedFree = freeRepository.save(free);
        return FreeSearchResponseDto.fromEntity(updatedFree);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        if (!freeRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }
        freeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FreeSearchResponseDto getPostById(Long id) {
        Free free = freeRepository.findByIdWithComments(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return FreeSearchResponseDto.fromEntity(free);
    }
}
