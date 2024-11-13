package com.jinsungwon99.post.repository;

import com.jinsungwon99.message.application.interfaces.MessageRepository;
import com.jinsungwon99.message.repository.JpaFcmTokenRepository;
import com.jinsungwon99.post.application.Interfaces.LikeCommentRepository;
import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.post.repository.entity.comment.CommentEntity;
import com.jinsungwon99.post.repository.entity.like.LikeEntity;
import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.post.repository.jpa.JpaCommentRepository;
import com.jinsungwon99.post.repository.jpa.JpaLikeRepository;
import com.jinsungwon99.post.repository.jpa.JpaPostRepository;
import com.jinsungwon99.user.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImpl implements LikeCommentRepository, LikePostRepository {

  @PersistenceContext
  private final EntityManager entityManager; //JPA 영속성 컨텍스트

  private final JpaLikeRepository jpaLikeRepository;
  private final JpaPostRepository jpaPostRepository;
  private final JpaCommentRepository jpaCommentRepository;
  private final MessageRepository messageRepository;
    @Override
    public boolean checkLike(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment,user);

        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(User user, Comment comment) {
        LikeEntity likeEntity = new LikeEntity(comment,user);

        entityManager.persist(likeEntity);

        //commentEntity LikeCount 증가
        jpaCommentRepository.updateLikeCommentEntity(comment.getId(), 1);

        //좋아요 알림 메시지 보내기
        messageRepository.sendLikeMessage(user,comment.getAuthor());

    }

    @Override
    @Transactional
    public void unlike(User user, Comment comment) {
       LikeEntity likeEntity = new LikeEntity(comment,user);

        jpaLikeRepository.deleteById(likeEntity.getId());

        //commentEntity LikeCount 감소
        jpaCommentRepository.updateLikeCommentEntity(comment.getId(), -1);
    }

    @Override
    public boolean checkLike(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post,user);

        return jpaLikeRepository.existsById(likeEntity.getId());
    }

    @Override
    @Transactional
    public void like(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post,user);
        //select 하지 않고 EntityManager 에 저장
        // = jpaLikeRepository.save(likeEntity); => select 로 조회 후 저장할 값 merge
        entityManager.persist(likeEntity);

        //postEntity LikeCount 증가
        jpaPostRepository.updateLikePostEntity(post.getId(), 1);
        
        //좋아요 알림 메시지 보내기
        messageRepository.sendLikeMessage(user,post.getAuthor());
    }

    @Override
    @Transactional
    public void unlike(User user, Post post) {
        LikeEntity likeEntity = new LikeEntity(post,user);

        jpaLikeRepository.deleteById(likeEntity.getId());

        //postEntity LikeCount 감소
        jpaPostRepository.updateLikePostEntity(post.getId(), -1);
    }
}
