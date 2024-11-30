package com.jinsungwon99.user.repository;

import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.repository.entity.UserEntity;
import com.jinsungwon99.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor  // 필요한 생성자만 자동 생성
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    @Transactional
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        userEntity = jpaUserRepository.save(userEntity);

        return userEntity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = jpaUserRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);

        return userEntity.toUser();
    }
}