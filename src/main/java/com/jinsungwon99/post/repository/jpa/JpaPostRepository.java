package com.jinsungwon99.post.repository.jpa;

import com.jinsungwon99.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPostRepository extends JpaRepository<PostEntity,Long> {

}
