package com.hugudungs.hugupjigup.board.notice.service;

import com.hugudungs.hugupjigup.board.notice.data.NoticeRepository;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import com.hugudungs.hugupjigup.data.entity.board.Notice;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.user.data.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    public NoticeResponseDto createNotice(NoticeRequestDto requestDto) throws Exception {
        try {
            //FIXME: 현재 로그인한 유저의 아이디 가져오는 로직
            //FIXME: userId로 관리자를 판별하는 로직
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));
            User user = userRepository.findById(1L) // 실제 존재하는 ID 사용
                    .orElseGet(() -> {
                        User newUser = new User();
                        // 필요한 User 필드 설정
                        return userRepository.save(newUser);
                    });

            Notice notice = Notice.builder()
                    .boardType(requestDto.getBoardType())
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .author(user)
                    .build();

            Notice savedNotice = noticeRepository.save(notice);

            return NoticeResponseDto.builder()
                    .noticeId(savedNotice.getId())
                    .boardType(savedNotice.getBoardType())
                    .noticeTitle(savedNotice.getTitle())
                    .noticeContent(savedNotice.getContent())
                    .noticeViews(savedNotice.getViews())
                    .authorId(savedNotice.getAuthor() != null ? savedNotice.getAuthor().getId() : null)
                    .createDate(savedNotice.getCreatedAt())
                    .updateDate(savedNotice.getUpdatedAt())
                    .build();
        } catch (DataAccessException e) {
            log.error("Database error details: ", e); // 원본 예외 로깅
            throw new Exception("게시글 생성 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 생성 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public NoticeResponseDto updateNotice(Long noticeId, NoticeRequestDto requestDto) throws Exception {
        try {
            Notice post = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new RuntimeException("해당 공지 게시글이 존재하지 않습니다."));

            post.setTitle(requestDto.getTitle());
            post.setContent(requestDto.getContent());

            Notice updatedNotice = noticeRepository.saveAndFlush(post);

            NoticeResponseDto responseDto = NoticeResponseDto.builder()
                    .noticeId(updatedNotice.getId())
                    .noticeTitle(updatedNotice.getTitle())
                    .noticeContent(updatedNotice.getContent())
                    .boardType(updatedNotice.getBoardType())
                    .updateDate(updatedNotice.getUpdatedAt())
                    .build();

            return responseDto;
        } catch (DataAccessException e) {
            throw new Exception("게시글 수정 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 수정 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public void deleteNotice(Long noticeId) throws Exception {
        try {
            //FIXME: 현재 로그인한 유저의 아이디 가져오는 로직
//            Long currentUserId = getCurrentUserId();
            Long currentUserId = 1L;

            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new RuntimeException("해당 공지 게시글이 존재하지 않습니다."));

            if (notice.getAuthor() == null) {
                throw new RuntimeException("작성자가 존재하지 않습니다.");
            }

            Long authorId = notice.getAuthor().getId();

            if (!authorId.equals(currentUserId)) {
                throw new RuntimeException("작성자만 삭제할 수 있습니다.");
            }

            noticeRepository.deleteById(noticeId);
        } catch (DataAccessException e) {
            throw new Exception("게시글 삭제 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 삭제 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public NoticeResponseDto getNoticeById(Long noticeId) throws Exception {
        try {
            Notice notice = noticeRepository.findById(noticeId)
                    .orElseThrow(() -> new RuntimeException("해당 공지 게시글이 존재하지 않습니다."));

            if (notice.getAuthor() == null) {
                throw new RuntimeException("작성자가 존재하지 않습니다.");
            }

            NoticeResponseDto responseDto = NoticeResponseDto.builder()
                    .noticeId(notice.getId())
                    .createDate(notice.getCreatedAt())
                    .updateDate(notice.getUpdatedAt())
                    .boardType(notice.getBoardType())
                    .noticeContent(notice.getContent())
                    .noticeTitle(notice.getTitle())
                    .noticeViews(notice.getViews())
                    .authorId(notice.getAuthor().getId())
                    .build();

            return responseDto;
        } catch (RuntimeException e) {
            throw new Exception("게시글 조회 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }

    @Override
    public Page<NoticeResponseDto> getNoticePosts(Pageable pageable) throws Exception {
        try {
            Page<Notice> noticePage = noticeRepository.findAll(pageable);

            return noticePage.map(notice -> NoticeResponseDto.builder()
                    .noticeId(notice.getId())
                    .createDate(notice.getCreatedAt())
                    .updateDate(notice.getUpdatedAt())
                    .boardType(notice.getBoardType())
                    .noticeContent(notice.getContent())
                    .noticeTitle(notice.getTitle())
                    .noticeViews(notice.getViews())
                    .authorId(notice.getAuthor() != null ? notice.getAuthor().getId() : null)
                    .build()
            );
        } catch (DataAccessException e) {
            throw new Exception("게시글 목록 조회 중 데이터베이스 오류가 발생했습니다.", e);
        } catch (Exception e) {
            throw new Exception("게시글 목록 조회 중 예기치 않은 오류가 발생했습니다.", e);
        }
    }
}