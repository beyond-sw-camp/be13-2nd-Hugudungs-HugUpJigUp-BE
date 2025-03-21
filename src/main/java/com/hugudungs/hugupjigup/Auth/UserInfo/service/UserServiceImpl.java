package com.hugudungs.hugupjigup.auth.UserInfo.service;

import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMenteeProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserMentorProfileDTO;
import com.hugudungs.hugupjigup.common.enums.ProfileType;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UpdateUserProfileDTO;
import com.hugudungs.hugupjigup.Auth.UserInfo.dto.user.UserProfileResponseDTO;
import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import com.hugudungs.hugupjigup.user.data.UserRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.UserProfileRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.PostRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.CommentRepository;
import com.hugudungs.hugupjigup.Auth.UserInfo.repository.MatchingCommentRepository;
import com.hugudungs.hugupjigup.matching.data.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.hugudungs.hugupjigup.Auth.UserInfo.service.UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final MatchingRepository matchingRepository;
    private final MatchingCommentRepository matchingCommentRepository;

    // 유저 정보 조회
    @Override
    public UserProfileResponseDTO getUserProfileByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfile userProfile = userProfileRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        int writtenPostsCount = Optional.ofNullable(postRepository.countByUser(user)).orElse(0);

        int writtenCommentsCount = Optional.ofNullable(commentRepository.countByUser(user)).orElse(0);

        int mentoringOpenedCount = Optional.ofNullable(matchingRepository.countByUser(user)).orElse(0);

        int menteeJoinedCount = Optional.ofNullable(matchingCommentRepository.countByUserComments(user)).orElse(0);

        String mentorIntroduction = "자기 소개 추가해주세요";
        String menteeIntroduction = "자기 소개 추가해주세요";

        if (userProfile.getProfileType() == ProfileType.MENTOR) {
            mentorIntroduction = userProfile.getIntroduction();
        } else if (userProfile.getProfileType() == ProfileType.MENTEE) {
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

    // 유저 기본 프로필 수정
    @Override
    public UpdateUserProfileDTO updateUserProfile(String email, UpdateUserProfileDTO updateUserProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        user.setPassword(updateUserProfileDTO.getPassword());
        user.setNickName(updateUserProfileDTO.getName());
        user.setEmail(updateUserProfileDTO.getEmail());

        userRepository.saveAndFlush(user);

//        user = User.builder()
//                .password(updateUserProfileDTO.getPassword())
//                .nickName(updateUserProfileDTO.getName())
//                .email(updateUserProfileDTO.getEmail())
//                .build();

//        return UpdateUserProfileDTO.builder()
//                .name(usersave.getNickName())
//                .email(usersave.getEmail())
//                .password(usersave.getPassword())
//                .build();
//
//        User usersave = userRepository.saveAndFlush(user);

        return new UpdateUserProfileDTO(
                user.getNickName(),
                user.getEmail(),
                user.getPassword()
        );

    }

    // 멘티 프로필 수정
    @Override
    public UpdateUserMentorProfileDTO updateUserMentorProfile(String email, UpdateUserMentorProfileDTO updateUserMentorProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfile userProfile = userProfileRepository.findMentorProfileByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        userProfile.setCurrentJob(updateUserMentorProfileDTO.getCurrentJob());
        userProfile.setIntroduction(updateUserMentorProfileDTO.getIntroduction());
        userProfile.setExperience(updateUserMentorProfileDTO.getExperience());

//        UserProfile.builder()
//                .currentJob(updateUserMentorProfileDTO.getCurrentJob())
//                .introduction(updateUserMentorProfileDTO.getIntroduction())
//                .experience(updateUserMentorProfileDTO.getExperience())
//                .build();  // 바로 새로운 객체를 생성해서 리턴

        userProfileRepository.saveAndFlush(userProfile);

        return new UpdateUserMentorProfileDTO(
                userProfile.getCurrentJob(),
                userProfile.getIntroduction(),
                userProfile.getExperience()
        );
    }

    // 멘토 프로필 수정
    @Override
    public UpdateUserMenteeProfileDTO updateUserMenteeProfile(String email, UpdateUserMenteeProfileDTO updateUserMenteeProfileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        UserProfile userProfile = userProfileRepository.findMenteeProfileByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("유저 프로필을 찾을 수 없습니다."));

        userProfile.setDesiredJob(updateUserMenteeProfileDTO.getDesiredJob());
        userProfile.setIntroduction(updateUserMenteeProfileDTO.getIntroduction());
        userProfile.setExperience(updateUserMenteeProfileDTO.getExperience());


//        UserProfile.builder()
//                .desiredJob(updateUserMenteeProfileDTO.getDesiredJob())
//                .introduction(updateUserMenteeProfileDTO.getIntroduction())
//                .experience(updateUserMenteeProfileDTO.getExperience())
//                .build();

        userProfileRepository.saveAndFlush(userProfile);

        return new UpdateUserMenteeProfileDTO(
                userProfile.getDesiredJob(),
                userProfile.getIntroduction(),
                userProfile.getExperience()
        );
    }
}
