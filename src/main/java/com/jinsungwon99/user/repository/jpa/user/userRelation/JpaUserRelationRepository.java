package com.jinsungwon99.user.repository.jpa.user.userRelation;

import com.jinsungwon99.user.repository.entity.UserRelationEntity;
import com.jinsungwon99.user.repository.entity.UserRelationIdEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {


    @Query("SELECT u.followingUserId FROM UserRelationEntity u "
        + " WHERE u.followerUserId = :authorId ")
    List<Long> findFollowers(Long authorId);
}
