package com.jinsungwon99.post.application.Interfaces;

import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.user.domain.User;

public interface LikePostRepository {
    boolean checkLike(User user, Post post);
    void like(User user,Post post);
    void unlike(User user,Post post);
}
