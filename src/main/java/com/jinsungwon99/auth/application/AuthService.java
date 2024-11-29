package com.jinsungwon99.auth.application;

import com.jinsungwon99.auth.application.Interfaces.EmailVerificationRepository;
import com.jinsungwon99.auth.application.Interfaces.UserAuthRepository;
import com.jinsungwon99.auth.application.dto.CreateUserAuthRequestDto;
import com.jinsungwon99.auth.application.dto.LoginRequestDto;
import com.jinsungwon99.auth.application.dto.UserAccessTokenResponseDto;
import com.jinsungwon99.auth.domain.Email;
import com.jinsungwon99.auth.domain.TokenProvider;
import com.jinsungwon99.auth.domain.UserAuth;
import com.jinsungwon99.message.repository.JpaFcmTokenRepository;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;
    private final JpaFcmTokenRepository jpaFcmTokenRepository;

    @Transactional
    public Long registerUser(CreateUserAuthRequestDto dto) {

        // 가입 전 이메일 인증 여부 확인 -> 인증 된 이메일만 가입 가능
        Email email = Email.createEmail(dto.email());

        if (!emailVerificationRepository.isEmailVerified(email)) {
            throw new IllegalArgumentException("인증되지 않은 이메일입니다.");
        }

        // User 생성
        UserInfo userInfo = new UserInfo(dto.name(), "Default-Profile.png");
        User user = new User(null, userInfo);

        // UserAuth 생성
        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), "USER");

        // DB 저장
        userAuth = userAuthRepository.registerUser(userAuth, user);

        return userAuth.getUserId();
    }

    @Transactional
    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password(), dto.fcmToken());

        // 정상 로그인 시 -> 토큰 생성
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getUserRole());

        return new UserAccessTokenResponseDto(token);
    }

    /*
        FCM 토큰 삭제
     */
    @Transactional
    public void deleteFcmToken(Long userId) {
        jpaFcmTokenRepository.deleteById(userId);
    }

}