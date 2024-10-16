package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.CommentRepository;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.post.repository.entity.comment.CommentEntity;
import com.jinsungwon99.post.repository.jpa.JpaCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{

    private final JpaCommentRepository jpaCommentRepository;

    @Override
    public Comment save(Comment comment) {
        CommentEntity entity = new CommentEntity(comment);
        entity = jpaCommentRepository.save(entity);
        return entity.toComment();
    }

    @Override
    public Comment findById(Long id) {
        CommentEntity entity = jpaCommentRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException :: new);
        return entity.toComment();

    }
}
