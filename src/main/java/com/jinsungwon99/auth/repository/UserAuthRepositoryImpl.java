package com.jinsungwon99.auth.repository;

import com.jinsungwon99.auth.application.Interfaces.UserAuthRepository;
import com.jinsungwon99.auth.domain.UserAuth;
import com.jinsungwon99.auth.repository.entity.UserAuthEntity;
import com.jinsungwon99.auth.repository.jpa.JpaUserAuthRepository;
import com.jinsungwon99.common.domain.exception.ErrorCode;
import com.jinsungwon99.common.ui.BaseException;
import com.jinsungwon99.message.repository.JpaFcmTokenRepository;
import com.jinsungwon99.message.repository.entity.FcmTokenEntity;
import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;
    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    @Override
    public UserAuth registerUser(UserAuth auth, User user) {
        // 회원 정보 (User) 저장
        User savedUser = userRepository.save(user);

        // UserAuth 저장
        UserAuthEntity userAuthEntity = new UserAuthEntity(auth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);

        return userAuthEntity.toUserAuth();
    }

    /*
        로그인
     */
    @Override
    @Transactional
    public UserAuth loginUser(String email, String password, String fcmToken) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("옳지 않은 비밀번호 입니다.");
        }

        userAuthEntity.updateLastLoginAt();

        // 기존의 같은 FCM 토큰을 가지고 있는 정보 삭제
        jpaFcmTokenRepository.deleteAllByFcmToken(fcmToken);

        // FCM 알림 토큰 저장
        jpaFcmTokenRepository.save(new FcmTokenEntity(userAuth.getUserId(), fcmToken));

        return userAuth;
    }

    @Override
    public UserAuth getAuth(Long userId) {
        return jpaUserAuthRepository.findByUserId(userId)
            .map(entity -> new UserAuth(entity.getEmail(), entity.getPassword(), entity.getUserRole(),
                entity.getUserId()))
            .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public void save(UserAuth userAuth) {

        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, userAuth.getUserId());

        jpaUserAuthRepository.save(userAuthEntity);
    }
}