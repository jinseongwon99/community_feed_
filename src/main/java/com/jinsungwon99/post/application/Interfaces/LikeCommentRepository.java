package com.jinsungwon99.post.application.Interfaces;

import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.user.domain.User;

public interface LikeCommentRepository {
    boolean checkLike(User user, Comment comment);
    void like(User user,Comment comment);
    void unlike(User user,Comment comment);
}
