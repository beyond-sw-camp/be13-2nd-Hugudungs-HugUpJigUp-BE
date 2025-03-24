package com.hugudungs.hugupjigup.userInfo.repository;

import com.hugudungs.hugupjigup.data.entity.user.User;
import com.hugudungs.hugupjigup.data.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<List<UserProfile>> findByUser(User user);

    // JPQL을 사용한 방법 (멘토 프로필 조회)
    @Query("SELECT up FROM UserProfile up WHERE up.user.id = :userId AND up.profileType = 'MENTOR'")
    Optional<UserProfile> findMentorProfileByUserId(@Param("userId") Long userId);

    // JPQL을 사용한 방법 (멘티 프로필 조회)
    @Query("SELECT up FROM UserProfile up WHERE up.user.id = :userId AND up.profileType = 'MENTEE'")
    Optional<UserProfile> findMenteeProfileByUserId(@Param("userId") Long userId);
}
