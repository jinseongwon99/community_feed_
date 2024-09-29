package com.jinsungwon99.user.application.interfaces;

import com.jinsungwon99.user.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user,User targetUser);
    void save(User user,User targetUser);
    void delete(User user,User targetUser);

}
