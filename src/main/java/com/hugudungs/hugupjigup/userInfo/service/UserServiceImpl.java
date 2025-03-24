package com.hugudungs.hugupjigup.userInfo.service;

import com.hugudungs.hugupjigup.userInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.userInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.common.enums.ProfileType;
import com.hugudungs.hugupjigup.userInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.userInfo.dto.user.UserProfileResponseDTO;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import com.hugudungs.hugupjigup.userInfo.repository.UserRepository;
import com.hugudungs.hugupjigup.userInfo.repository.UserProfileRepository;
import com.hugudungs.hugupjigup.userInfo.repository.PostRepository;
import com.hugudungs.hugupjigup.userInfo.repository.CommentRepository;
import com.hugudungs.hugupjigup.userInfo.repository.MatchingCommentRepository;
import com.hugudungs.hugupjigup.matching.data.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingCommentRepository matchingCommentRepository;

    // 유저 정보 조회
    @Override
    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        System.out.println(user);

        List<UserProfile> userProfile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        UserProfile mentorProfile = userProfile.stream()
                .filter(profile -> profile.getProfileType() == ProfileType.MENTOR)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("멘토 프로필을 찾을 수 없습니다."));

        UserProfile menteeProfile = userProfile.stream()
                .filter(profile -> profile.getProfileType() == ProfileType.MENTEE)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("멘티 프로필을 찾을 수 없습니다."));

        int writtenPostsCount = Optional.ofNullable(postRepository.countByUser(user)).orElse(0);

        int writtenCommentsCount = Optional.ofNullable(commentRepository.countByUser(user)).orElse(0);

        int mentoringOpenedCount = Optional.ofNullable(matchingRepository.countByUser(user)).orElse(0);

        int menteeJoinedCount = Optional.ofNullable(matchingCommentRepository.countByUserComments(user)).orElse(0);

        return UserProfileResponseDTO.builder()
                .nickname(user.getNickName())
                .email(user.getEmail())
                .mentorProfile(mentorProfile)
                .menteeProfile(menteeProfile)
                .postCount(writtenPostsCount)
                .commentCount(writtenCommentsCount)
                .matchingCount(mentoringOpenedCount)
                .matchingCommentCount(menteeJoinedCount)
                .build();
    }

    // 유저 기본 프로필 수정
    @Override
    public UpdateUserProfileDTO updateUserProfile(Long userId, UpdateUserProfileDTO updateUserProfileDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        user.setPassword(updateUserProfileDTO.getPassword());
        user.setNickName(updateUserProfileDTO.getName());
        user.setEmail(updateUserProfileDTO.getEmail());

        userRepository.saveAndFlush(user);

        return new UpdateUserProfileDTO(
                user.getNickName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    // 멘티 프로필 수정
    @Override
    public UpdateUserMentorProfileDTO updateUserMentorProfile(Long userId, UpdateUserMentorProfileDTO updateUserMentorProfileDTO) {
        User user = userRepository.findById(userId)
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

    // 멘토 프로필 수정
    @Override
    public UpdateUserMenteeProfileDTO updateUserMenteeProfile(Long userId, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO) {
        User user = userRepository.findById(userId)
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
