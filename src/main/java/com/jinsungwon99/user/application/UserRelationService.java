package com.jinsungwon99.user.application;

import com.jinsungwon99.user.application.dto.FollowUserRequestDto;
import com.jinsungwon99.user.application.interfaces.UserRelationRepository;
import com.jinsungwon99.user.domain.User;

public class UserRelationService {

    private final UserService userService;
    private final UserRelationRepository userRelationRepository;

    public UserRelationService(UserService userService,
        UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user,targetUser)){
            throw new IllegalArgumentException();
        }

        user.follow(targetUser);
        userRelationRepository.save(user,targetUser);
    }

    public void unfollow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user,targetUser)){
            throw new IllegalArgumentException();
        }

        user.unfollow(targetUser);
        userRelationRepository.delete(user,targetUser);
    }
}
