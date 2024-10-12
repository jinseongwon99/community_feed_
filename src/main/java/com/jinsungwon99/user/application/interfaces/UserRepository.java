package com.jinsungwon99.user.application.interfaces;

import com.jinsungwon99.user.domain.User;

public interface UserRepository {

    User save(User user);
    User findById(Long id);
}
