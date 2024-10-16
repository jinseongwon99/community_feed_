package com.jinsungwon99.post.repository.jpa;

import com.jinsungwon99.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCommentRepository extends JpaRepository<CommentEntity,Long> {

}
