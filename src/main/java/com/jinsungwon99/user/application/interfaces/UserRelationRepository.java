package com.jinsungwon99.user.application.interfaces;

import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.ui.dto.GetUserRelationListResponseDto;
import java.util.List;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);

    void save(User user, User targetUser);

    void delete(User user, User targetUser);

    List<GetUserRelationListResponseDto> findAllByFollowingUserId(Long followingUserId);

    List<GetUserRelationListResponseDto> findAllByFollowerUserId(Long followerUserId);
}