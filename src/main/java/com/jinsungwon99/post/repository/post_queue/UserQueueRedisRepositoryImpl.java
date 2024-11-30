package com.jinsungwon99.post.repository.post_queue;

import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.post.repository.entity.post.UserPostQueueEntity;
import com.jinsungwon99.post.repository.jpa.JpaUserPostQueueRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"!test"})
@RequiredArgsConstructor
public class UserQueueRedisRepositoryImpl implements UserQueueRedisRepository {

    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;

    /*
        [포스트 작성시 배포] 팔로우 한 유저에게 -> 피드 추가
     */
    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for (Long userId: userIdList) {

            UserPostQueueEntity userPostQueueEntity = new UserPostQueueEntity(
                userId,
                postEntity.getId(),
                postEntity.getAuthor().getId()
            );

            jpaUserPostQueueRepository.save(userPostQueueEntity);
        }
    }

    /*
        [팔로우 시 기존 포스트 배포]
     */
    @Override
    public void publishPostToFollowerUserList(List<PostEntity> postEntities, Long userId) {
        for (PostEntity postEntity : postEntities) {
            UserPostQueueEntity userPostQueueEntity = new UserPostQueueEntity(
                userId,
                postEntity.getId(),
                postEntity.getAuthor().getId()
            );

            jpaUserPostQueueRepository.save(userPostQueueEntity);
        }
    }

    @Override
    public void deleteFeed(Long userId, Long targetUserId) {
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetUserId);
    }

}