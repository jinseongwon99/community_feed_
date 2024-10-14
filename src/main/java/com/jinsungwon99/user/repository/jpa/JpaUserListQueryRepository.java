package com.jinsungwon99.user.repository.jpa;

import com.jinsungwon99.user.application.dto.GetUserListResponseDto;
import com.jinsungwon99.user.repository.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserListQueryRepository extends JpaRepository<UserEntity,Long> {

    @Query(value = "SELECT new com.jinsungwon99.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) "
        + "FROM UserRelationEntity ur "
        + "INNER JOIN UserEntity u ON ur.followerUserId = u.Id "
        + "WHERE ur.followingUserId = :userId")  //JPQL 사용하기 위해 작성
    List<GetUserListResponseDto> getFollowingUserList(Long userId);

    @Query(value = "SELECT new com.jinsungwon99.user.application.dto.GetUserListResponseDto(u.name, u.profileImageUrl) "
        + "FROM UserRelationEntity ur "
        + "INNER JOIN UserEntity u ON ur.followingUserId = u.Id "
        + "WHERE ur.followerUserId = :userId")
    List<GetUserListResponseDto> getFollowerUserList(Long userId);
}
