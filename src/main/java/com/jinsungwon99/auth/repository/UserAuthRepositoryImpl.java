package com.jinsungwon99.auth.repository;

import com.jinsungwon99.auth.application.Interfaces.UserAuthRepository;
import com.jinsungwon99.auth.domain.UserAuth;
import com.jinsungwon99.auth.repository.entity.UserAuthEntity;
import com.jinsungwon99.auth.repository.jpa.JpaUserAuthRepository;
import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    public UserAuth registerUser(UserAuth auth, User user) {
        //회원 정보 (User) 저장
        User savedUser = userRepository.save(user);

        //UserAuth 저장
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);

        return userAuthEntity.toUserAuth();
    }
    
    //로그인
    @Override
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if(!userAuth.matchPassword(password)){
            throw new IllegalArgumentException("옳지 않은 비밀번호 입니다");
        }
        return userAuth;
    }
}
