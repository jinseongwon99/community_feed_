package com.jinsungwon99.post.application.Interfaces;

import com.jinsungwon99.post.domain.comment.Comment;

public interface CommentRepository {

    Comment save(Comment comment);
    Comment findById(Long id);
}
