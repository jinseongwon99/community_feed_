package com.jinsungwon99.auth.application.Interfaces;

import com.jinsungwon99.auth.domain.UserAuth;
import com.jinsungwon99.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth auth, User user);
}
