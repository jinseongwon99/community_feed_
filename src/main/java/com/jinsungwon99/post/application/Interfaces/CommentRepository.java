package com.jinsungwon99.post.application.Interfaces;

import com.jinsungwon99.post.domain.comment.Comment;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
