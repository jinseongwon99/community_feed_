package com.jinsungwon99.post.repository.post_queue;

import com.jinsungwon99.post.repository.entity.post.PostEntity;
import java.util.List;


public interface UserQueueRedisRepository {

    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostToFollowerUserList(List<PostEntity> postEntityList,Long userId );
    void deleteFeed (Long userId,Long authorId);
    void deletePostFromFollowingUserList(Long postId, List<Long> followerIds);
}
