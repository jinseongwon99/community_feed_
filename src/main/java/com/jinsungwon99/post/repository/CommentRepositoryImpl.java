package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.CommentRepository;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.post.repository.entity.comment.CommentEntity;
import com.jinsungwon99.post.repository.jpa.JpaCommentRepository;
import com.jinsungwon99.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository{

    private final JpaCommentRepository jpaCommentRepository;

    private final JpaPostRepository jpaPostRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        Post post = comment.getPost();  //해당 Post 의 commentCount 값 1 증가
        CommentEntity entity = new CommentEntity(comment);
        entity = jpaCommentRepository.save(entity);
        jpaPostRepository.increaseCommentCount(post.getId());
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
