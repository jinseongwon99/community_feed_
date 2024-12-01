package com.jinsungwon99.user.application.interfaces;

import com.jinsungwon99.user.domain.User;
import java.util.List;

public interface UserRepository {

    User save(User user);

    User findById(Long id);

    List<User> findByNameContaining(String name);
}