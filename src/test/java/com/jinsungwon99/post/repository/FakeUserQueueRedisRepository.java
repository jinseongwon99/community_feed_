package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.post.repository.post_queue.UserQueueRedisRepository;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")    // 테스트 환경에서만 실행
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {

        for (Long userId: userIdList) {
            if (queue.containsKey(userId)) {
                queue.get(userId).add(postEntity);
            } else {
                queue.put(userId, new HashSet<>(List.of(postEntity)));
            }
        }
    }

    @Override
    public void publishPostToFollowerUserList(List<PostEntity> postEntityList, Long userId) {

        if(queue.containsKey(userId)){
            queue.get(userId).addAll(postEntityList);
        }
        else {
            queue.put(userId,new HashSet<>(postEntityList));
        }
    }

    @Override
    public void deleteFeed(Long userId, Long authorId) {

        if(queue.containsKey(userId)){
            queue.get(userId).removeIf(post -> post.getAuthor().getId().equals(authorId));
        }
    }

    public List<PostEntity> getPostListByUserId(Long userId){
        return List.copyOf(queue.get(userId));
    }
}
