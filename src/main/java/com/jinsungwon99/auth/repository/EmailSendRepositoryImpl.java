package com.jinsungwon99.auth.repository;

import com.jinsungwon99.auth.application.Interfaces.EmailSendRepository;
import com.jinsungwon99.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String token) {
        //TODO
    }
}
