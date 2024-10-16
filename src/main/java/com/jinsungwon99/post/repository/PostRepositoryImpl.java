package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.post.repository.jpa.JpaPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;


    @Override
    public Post save(Post post) {
        PostEntity entity = new PostEntity(post);
        entity = jpaPostRepository.save(entity);
        return entity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity entity = jpaPostRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return entity.toPost();
    }
}
