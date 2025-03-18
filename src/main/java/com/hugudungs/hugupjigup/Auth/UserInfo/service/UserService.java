package com.hugudungs.hugupjigup.Auth.UserInfo.service;

import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.common.enums.ProfileType;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UserProfileResponseDTO;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.UserRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.UserProfileRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.PostRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.CommentRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.MatchingRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.MatchingCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingCommentRepository matchingCommentRepository;

    public UserProfileResponseDTO getUserProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfile userProfile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        // 유저가 작성한 게시글 수 계산 (기본값은 0)
        int writtenPostsCount = Optional.ofNullable(postRepository.countByUser(user)).orElse(0);

        // 유저가 작성한 댓글 수 계산 (기본값은 0)
        int writtenCommentsCount = Optional.ofNullable(commentRepository.countByUser(user)).orElse(0);

        // 멘토링 개설 수 계산 (기본값은 0)
        int mentoringOpenedCount = Optional.ofNullable(matchingRepository.countByUser(user)).orElse(0);

        // 멘티 참여 수 계산 (기본값은 0)
        int menteeJoinedCount = Optional.ofNullable(matchingCommentRepository.countByUserComments(user)).orElse(0);

        // 여기에 멘토와 멘티에 대한 자기 소개 가져올거임 기본값을 '자기 소개 추가해주세요'로
        // 멘토와 멘티에 대한 자기소개를 구별하여 가져오기
        String mentorIntroduction = "자기 소개 추가해주세요";
        String menteeIntroduction = "자기 소개 추가해주세요";

        // 멘토 프로필일 경우 자기소개 설정
        if (userProfile.getProfileType() == ProfileType.MENTOR) {
            mentorIntroduction = userProfile.getIntroduction();
        }
        // 멘티 프로필일 경우 자기소개 설정
        else if (userProfile.getProfileType() == ProfileType.MENTEE) {
            menteeIntroduction = userProfile.getIntroduction();
        }

        return new UserProfileResponseDTO(
                user.getNickName(),
                userProfile.getCurrentJob(),
                userProfile.getDesiredJob(),
                writtenPostsCount,
                writtenCommentsCount,
                mentoringOpenedCount,
                menteeJoinedCount,
                mentorIntroduction,
                menteeIntroduction
        );
    }

    // 기존의 updateUserProfile 메서드 유지
    public UpdateUserProfileDTO updateUserProfile(String email, UpdateUserProfileDTO updateUserProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        // 아직 암호화는 추가 안함 조건도 아직 없음
        user.setPassword(updateUserProfileDTO.getPassword());

        // 이름과 이메일을 수정
        user.setNickName(updateUserProfileDTO.getName());
        user.setEmail(updateUserProfileDTO.getEmail());

        userRepository.saveAndFlush(user);

        return new UpdateUserProfileDTO(
                user.getNickName(),
                user.getEmail(),
                user.getPassword()
        );
    }


    public UpdateUserMentorProfileDTO updateUserMentorProfile(String email, UpdateUserMentorProfileDTO updateUserMentorProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfile userProfile = userProfileRepository.findMentorProfileByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        userProfile.setCurrentJob(updateUserMentorProfileDTO.getCurrentJob());
        userProfile.setIntroduction(updateUserMentorProfileDTO.getIntroduction());
        userProfile.setExperience(updateUserMentorProfileDTO.getExperience());

        userProfileRepository.saveAndFlush(userProfile);

        return new UpdateUserMentorProfileDTO(
            userProfile.getCurrentJob(),
            userProfile.getIntroduction(),
            userProfile.getExperience()
        );
    }

    public UpdateUserMenteeProfileDTO updateUserMenteeProfile(String email, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfile userProfile = userProfileRepository.findMenteeProfileByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        userProfile.setDesiredJob(updateUserMenteeProfileDTO.getDesiredJob());
        userProfile.setIntroduction(updateUserMenteeProfileDTO.getIntroduction());
        userProfile.setExperience(updateUserMenteeProfileDTO.getExperience());

        userProfileRepository.saveAndFlush(userProfile);

        return new UpdateUserMenteeProfileDTO(
                userProfile.getDesiredJob(),
                userProfile.getIntroduction(),
                userProfile.getExperience()
        );
    }
}
