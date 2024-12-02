package com.jinsungwon99.post.repository.jpa;

import com.jinsungwon99.post.repository.entity.comment.CommentEntity;
import com.jinsungwon99.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaCommentRepository extends JpaRepository<CommentEntity,Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c "
        + "SET c.likeCount = c.likeCount + :likeCount, "
        + "c.updDt = now() "
        + "where c.id = :commentId")
    void updateLikeCommentEntity(Long commentId, Integer likeCount);

    void deleteAllByPostId(Long postId);
}
