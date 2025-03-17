package com.hugudungs.hugupjigup.board.notice.service;

import com.hugudungs.hugupjigup.board.notice.data.NoticeRepository;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeRequestDto;
import com.hugudungs.hugupjigup.board.notice.data.dto.NoticeResponseDto;
import com.hugudungs.hugupjigup.common.enums.BoardType;
import com.hugudungs.hugupjigup.data.entity.board.Notice;
import com.hugudungs.hugupjigup.data.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NoticeServiceImpl implements NoticeService{
    private final NoticeRepository noticeRepository;
//    private final UserRepository userRepository;

    /**
     * NoticeRepository 생성
     * @param noticeRepository
     * @see NoticeRepository
     */
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }


    @Override
    public NoticeResponseDto createNotice(Long userId, NoticeRequestDto requestDto) {
        //FIXME: userId로 User를 가져오는 로직이 완성되면 추가
        //FIXME: userId로 관리자를 판별하는 로직
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자 ID입니다."));

        Notice notice = Notice.builder()
                .boardType(BoardType.NOTICE)
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .views(0)
//                .user(user)
                .build();

        Notice savedNotice = noticeRepository.save(notice);

        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .noticeId(savedNotice.getId())
                .boardType(savedNotice.getBoardType())
                .noticeTitle(savedNotice.getTitle())
                .noticeContent(savedNotice.getContent())
                .noticeViews(savedNotice.getViews())
                .authorId(savedNotice.getAuthor().getId())
                .build();

        return responseDto;
    }

    @Override
    public NoticeResponseDto updateNotice(Long noticeId, NoticeRequestDto requestDto) throws Exception {
        Notice post = noticeRepository.findById(noticeId).get();

        NoticeResponseDto responseDto = NoticeResponseDto.builder()
                .noticeId(noticeId)
                .noticeTitle(requestDto.getTitle())
                .noticeContent(requestDto.getTitle())
                .updateDate(LocalDateTime.now())
                .build();
        return responseDto;
    }

    @Override
    public void deleteNotice(Long noticeId) throws Exception {
        //FIXME: 현재 로그인한 유저의 아이디 가져오는 로직
//        Long currentUserId = getCurrentUserId();
        Long currentUserId = 1L;

        Long authorId = noticeRepository
                .findById(noticeId)
                .orElseThrow()
                .getAuthor()
                .getId();

        if (!authorId.equals(currentUserId)) {
//            throw new UnauthorizedException("작성자만 삭제 할 수 있습니다.");
            throw new RuntimeException("작성자만 삭제 할 수 있습니다.");
        }

        noticeRepository.deleteById(noticeId);
    }

    @Override
    public NoticeResponseDto getNoticeById(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow();

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
    }

    @Override
    public Page<NoticeResponseDto> getNoticePosts(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAll(pageable);

        return noticePage.map(notice -> NoticeResponseDto.builder()
                .noticeId(notice.getId())
                .createDate(notice.getCreatedAt())
                .updateDate(notice.getUpdatedAt())
                .boardType(notice.getBoardType())
                .noticeContent(notice.getContent())
                .noticeTitle(notice.getTitle())
                .noticeViews(notice.getViews())
                .authorId(notice.getAuthor().getId())
                .build()
        );
    }
}
