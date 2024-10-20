package com.jinsungwon99.user.repository;

import com.jinsungwon99.post.repository.post_queue.UserPostQueueCommandRepository;
import com.jinsungwon99.user.application.interfaces.UserRelationRepository;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.repository.entity.UserEntity;
import com.jinsungwon99.user.repository.entity.UserRelationEntity;
import com.jinsungwon99.user.repository.entity.UserRelationIdEntity;
import com.jinsungwon99.user.repository.jpa.JpaUserRepository;
import com.jinsungwon99.user.repository.jpa.user.userRelation.JpaUserRelationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpaUserRepository;
    private final UserPostQueueCommandRepository userPostQueueCommandRepository;
    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());

        return jpaUserRelationRepository.existsById(id);

    }

    @Override
    @Transactional  //메소드나 클래스의 작업들이 모두 성공하거나, 하나라도 실패하면 전체를 롤백하도록 보장
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        //저장 + 팔로우 count 증가
        jpaUserRelationRepository.save(entity);

        //변화된 팔로우 값 update
        jpaUserRepository.saveAll(List.of(new UserEntity(user),new UserEntity(targetUser)));

        //팔로우의 post를 피드에 저장
        userPostQueueCommandRepository.saveFollowPost(user.getId(),targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        //저장 + 팔로우 count 감소
        jpaUserRelationRepository.deleteById(id);

        //변화된 팔로우 값 update
        jpaUserRepository.saveAll(List.of(new UserEntity(user),new UserEntity(targetUser)));

        //언팔로우의 post를 피드에서 삭제
        userPostQueueCommandRepository.deleteFollowPost(user.getId(), targetUser.getId());
    }
}
