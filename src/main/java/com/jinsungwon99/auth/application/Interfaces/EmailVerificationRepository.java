package com.jinsungwon99.auth.application.Interfaces;

import com.jinsungwon99.auth.domain.Email;

public interface EmailVerificationRepository {
    void createEmailVerification(Email email,String token);

    void verifyEmail(Email email,String token);
    boolean isEmailVerified(Email email);
}
