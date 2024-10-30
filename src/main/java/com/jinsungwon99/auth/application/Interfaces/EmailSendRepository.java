package com.jinsungwon99.auth.application.Interfaces;

import com.jinsungwon99.auth.domain.Email;

public interface EmailSendRepository {

    void sendEmail(Email email,String token);
}
