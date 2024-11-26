package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.post.repository.jpa.JpaPostRepository;
import com.jinsungwon99.post.repository.post_queue.UserPostQueueCommandRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;

    @Override
    public Post save(Post post) {
        PostEntity entity = new PostEntity(post);
        if(entity.getId() != null){
            jpaPostRepository.updatePostEntity(entity);
            return entity.toPost();
        }
        entity = jpaPostRepository.save(entity);
        userPostQueueCommandRepository.publishPost(entity);
        return entity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity entity = jpaPostRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return entity.toPost();
    }
    @Override
    public List<Post> findAllByUserIdOrderByIdDesc(Long userId) {
        List<PostEntity> postEntityList = jpaPostRepository.findAllByAuthorIdOrderByIdDesc(userId);
        return postEntityList.stream().map(PostEntity::toPost).toList();
    }
}
