package com.hugudungs.hugupjigup.service;

import com.hugudungs.hugupjigup.data.entity.board.BoardTypeEntity;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.dto.board.free.FreeGenerationDto;
import com.hugudungs.hugupjigup.data.entity.board.Free;
import com.hugudungs.hugupjigup.dto.board.free.FreeInquireDto;
import com.hugudungs.hugupjigup.dto.board.free.FreeUpdateDto;
import com.hugudungs.hugupjigup.repository.board.BoardTypeRepository;
import com.hugudungs.hugupjigup.repository.board.FreeRepository;
import com.hugudungs.hugupjigup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreeService {
    private final FreeRepository freeRepository;
    private final UserRepository userRepository; // ✅ UserRepository 추가
    private final BoardTypeRepository boardTypeRepository;  // ✅ 추가

    // 게시글 생성
    @Transactional
    public Free createPost(FreeGenerationDto requestDto) {
        // 🔹 1. `userId`로 `User` 엔티티 조회
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        // ✅ Enum으로 들어온 `boardType`을 `BoardTypeEntity`로 변환
        BoardTypeEntity boardTypeEntity = boardTypeRepository.findByBoardType(requestDto.getBoardType())
                .orElseThrow(() -> new IllegalArgumentException("해당 BoardType이 존재하지 않습니다."));

        // 🔹 2. `Free` 엔티티 생성 (User 객체를 설정)
        Free free = Free.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .user(user)  // ✅ User 객체를 직접 넣음
                .boardType(boardTypeEntity)  // ✅ 추가
                .build();

        return freeRepository.save(free);
    }

    // 🔧 게시글 목록 조회 (검색, 정렬, 페이징)
    public Page<FreeInquireDto> getPosts(int page, int size, String keyword, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Free> freePage;
        if (keyword != null && !keyword.isEmpty()) {
            freePage = freeRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        } else {
            freePage = freeRepository.findAll(pageable);
        }

        return freePage.map(FreeInquireDto::fromEntity);
    }

    // ✅ 게시글 수정 기능
    @Transactional
    public Free updatePost(Long id, FreeUpdateDto requestDto) {
        // 1️⃣ 기존 게시글 찾기
        Free free = freeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 2️⃣ 수정할 필드 변경
        free.setTitle(requestDto.getTitle());
        free.setContent(requestDto.getContent());

        // 3️⃣ 저장 후 반환
        return freeRepository.save(free);
    }

    // ✅ 게시글 삭제 기능
    @Transactional
    public void deletePost(Long id) {
        // 1️⃣ 게시글이 존재하는지 확인
        if (!freeRepository.existsById(id)) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }

        // 2️⃣ 게시글 삭제
        freeRepository.deleteById(id);
    }

    // ✅ 특정 게시글 조회
    @Transactional(readOnly = true)
    public FreeInquireDto getPostById(Long id) {
        Free free = freeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        return new FreeInquireDto(
                free.getId(),  // ✅ ID 추가
                free.getBoardType().getBoardType().name(),  // Enum → String 변환
                free.getTitle(),
                free.getContent(),
                free.getUser().getNickName(),
                free.getViews(),  // ✅ Views 추가
                free.getCreatedAt()  // ✅ 생성 날짜 추가
        );
    }
}

