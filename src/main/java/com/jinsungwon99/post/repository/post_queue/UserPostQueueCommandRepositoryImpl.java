package com.jinsungwon99.post.repository.post_queue;

import com.jinsungwon99.post.repository.entity.post.PostEntity;
import com.jinsungwon99.post.repository.entity.post.UserPostQueueEntity;
import com.jinsungwon99.post.repository.jpa.JpaPostRepository;
import com.jinsungwon99.post.repository.jpa.JpaUserPostQueueRepository;
import com.jinsungwon99.user.repository.entity.UserEntity;
import com.jinsungwon99.user.repository.jpa.user.userRelation.JpaUserRelationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserPostQueueCommandRepositoryImpl implements UserPostQueueCommandRepository{

    private final JpaPostRepository jpaPostRepository;
    private final JpaUserPostQueueRepository jpaUserPostQueueRepository;
    private final JpaUserRelationRepository jpaUserRelationRepository;

    // post가 등록될 때 작성자를 팔로우하는 유저 피드에 해당 포스트 등록
    @Override
    @Transactional
    public void publishPost(PostEntity postEntity) {
        //작성자 정보 가져오기
        UserEntity author = postEntity.getAuthor();

        //작성자를 팔로우하는 유저 정보 가져오기
        List<Long> followerIds = jpaUserRelationRepository.findFollowers(author.getId());

        //팔로우하는 유저들의 리스트를 -> UserPostQueueEntity 로 변경
        List<UserPostQueueEntity> userPostQueueEntityList = followerIds.stream()
            .map(userId -> new UserPostQueueEntity(userId, postEntity.getId(), author.getId()))
            .toList();

        //DB 저장
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);

    }
    
    // 팔로우한 작성자에 post를 전부 피드에 등록
    @Override
    @Transactional
    public void saveFollowPost(Long userId, Long targetId) {
        //작성자의 post 가져오기
        List<Long> followingPosts= jpaPostRepository.findFollowingPosts(targetId);

        //Entity로 변경
        List<UserPostQueueEntity> userPostQueueEntityList = followingPosts.stream()
                .map(postId -> new UserPostQueueEntity(userId,postId,targetId))
                .toList();

        //DB저장
        jpaUserPostQueueRepository.saveAll(userPostQueueEntityList);

        
    }
    // 언팔로우 작성자에 post를 전부 삭제
    @Override
    @Transactional
    public void deleteFollowPost(Long userId, Long targetId) {
        // 삭제
        jpaUserPostQueueRepository.deleteAllByUserIdAndAuthorId(userId, targetId);
    }
}
