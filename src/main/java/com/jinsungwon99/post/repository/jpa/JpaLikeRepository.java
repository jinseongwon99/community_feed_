package com.jinsungwon99.post.repository.jpa;

import com.jinsungwon99.post.repository.entity.like.LikeEntity;
import com.jinsungwon99.post.repository.entity.like.LikeIdEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

    boolean existsById(LikeIdEntity likeIdEntity);
}
