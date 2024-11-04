package com.jinsungwon99.auth.application;

import com.jinsungwon99.auth.application.Interfaces.EmailSendRepository;
import com.jinsungwon99.auth.application.Interfaces.EmailVerificationRepository;
import com.jinsungwon99.auth.application.dto.SendEmailRequestDto;
import com.jinsungwon99.auth.domain.Email;
import com.jinsungwon99.auth.domain.RandomTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSendRepository emailSendRepository;
    private final EmailVerificationRepository emailVerificationRepository;

    public void sendEmail(SendEmailRequestDto dto){
        Email email = Email.createEmail(dto.email());
        String token = RandomTokenGenerator.generateToken();

        //이메일 인증 전송
        emailSendRepository.sendEmail(email,token);
        
        //DB에 저장
        emailVerificationRepository.createEmailVerification(email,token);
    }

    public void verifyEmail(String email,String token){
        Email emailValue = Email.createEmail(email);
        emailVerificationRepository.verifyEmail(emailValue,token);

    }
}
