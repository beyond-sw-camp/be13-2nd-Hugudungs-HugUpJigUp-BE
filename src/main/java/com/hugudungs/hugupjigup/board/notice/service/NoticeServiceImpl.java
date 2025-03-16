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
    public NoticeResponseDto updateNotice(Long noticeId, String title, String content) throws Exception {
        return null;
    }

    @Override
    public void deleteNotice(Long id) throws Exception {

    }

    @Override
    public Page<NoticeResponseDto> getNoticePosts(Pageable pageable) {
        return null;
    }

    @Override
    public NoticeResponseDto getNoticeById(Long noticeId) {
        return null;
    }
}
