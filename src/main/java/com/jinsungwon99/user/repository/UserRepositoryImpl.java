package com.jinsungwon99.user.repository;

import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.repository.entity.UserEntity;
import com.jinsungwon99.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor    //자동으로 생성자를 생성
//Fake 객체를 이용해서 기능으로 구현을 했던 것과 동일
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = jpaUserRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity entity = jpaUserRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);
        return entity.toUser();
    }
}
