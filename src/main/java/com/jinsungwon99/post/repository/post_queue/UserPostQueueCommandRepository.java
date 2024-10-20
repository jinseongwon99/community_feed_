package com.jinsungwon99.post.repository.post_queue;

import com.jinsungwon99.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {

    void publishPost(PostEntity postEntity);
    void saveFollowPost(Long userId, Long targetId);
    void deleteFollowPost(Long userId, Long targetId);
}
