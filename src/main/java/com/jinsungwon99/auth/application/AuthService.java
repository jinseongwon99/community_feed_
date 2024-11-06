package com.jinsungwon99.auth.application;

import com.jinsungwon99.auth.application.Interfaces.EmailVerificationRepository;
import com.jinsungwon99.auth.application.Interfaces.UserAuthRepository;
import com.jinsungwon99.auth.application.dto.CreateUserAuthRequestDto;
import com.jinsungwon99.auth.domain.Email;
import com.jinsungwon99.auth.domain.UserAuth;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public Long registerUser(CreateUserAuthRequestDto dto){

        //가입 전 이메일 인증 여부 확인 -> 인증 된 이메일만 가입 가능
        Email email = Email.createEmail(dto.email());
        if (!emailVerificationRepository.isEmailVerified(email)){
            throw new IllegalArgumentException("인증되자 않은 이메일입니다");
        }
        //User 생성
        UserInfo userInfo = new UserInfo(dto.email(), dto.profileImageUrl());
        User user = new User(null,userInfo);

        //UserAuth 생성
        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());

        //DB 저장
         userAuth = userAuthRepository.registerUser(userAuth,user);

         return userAuth.getUserId();
    }

}
