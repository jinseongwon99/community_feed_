package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.LikeCommentRepository;
import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.post.repository.entity.like.LikeEntity;
import com.jinsungwon99.post.repository.jpa.JpaLikeRepository;
import com.jinsungwon99.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeCommentRepository, LikePostRepository {

  private final JpaLikeRepository jpaLikeRepository;

    @Override
    public boolean checkLike(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment,user);

        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment,user);

        jpaLikeRepository.save(likeEntity);

    }

    @Override
    public void unlike(User user, Comment comment) {
       LikeEntity likeEntity = new LikeEntity(comment,user);

        jpaLikeRepository.deleteById(likeEntity.getId());
    }

    @Override
    public boolean checkLike(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post,user);

        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    public void like(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post,user);

        jpaLikeRepository.save(likeEntity);
    }

    @Override
    public void unlike(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post,user);

        jpaLikeRepository.deleteById(likeEntity.getId());
    }
}
