package com.jinsungwon99.user.repository.jpa.user.userRelation;

import com.jinsungwon99.user.repository.entity.UserRelationEntity;
import com.jinsungwon99.user.repository.entity.UserRelationIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    @Query("SELECT u.followingUserId FROM UserRelationEntity u "
        + "where u.followerUserId = :authorId")
    List<Long> findFollowers(Long authorId);

    @Query("SELECT u.followerUserId FROM UserRelationEntity u "
        + "where u.followingUserId = :followingUserId")
    List<Long> findAllByFollowingUserId(Long followingUserId);

    @Query("SELECT u.followingUserId FROM UserRelationEntity u "
        + "where u.followerUserId = :followerUserId")
    List<Long> findAllByFollowerUserId(Long followerUserId);
}