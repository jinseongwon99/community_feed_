package com.jinsungwon99.acceptance.auth;

import static com.jinsungwon99.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.jinsungwon99.acceptance.utils.AcceptanceTestTemplate;
import com.jinsungwon99.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email ="email@email.com";

    @BeforeEach
    void setUP(){
        this.cleanUp();
    }

    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved(){

        //given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = requestSendEmail(dto);

        //then
        String token = this.getEmailToken(email); //DB 에서 토큰 가져오기
        assertNotNull(token);
        assertEquals(0,code);
    }
    //유효하지 않은 이메일
    @Test
    void givenInvalidEmail_whenSendEmail_thenVerificationTokenNotSaved(){
        
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto("abcd");

        //when
        Integer code = requestSendEmail(dto);

        //then
        String token = this.getEmailToken(email); //DB 에서 토큰 가져오기
        assertNull(token);
        assertEquals(500,code); //HTTP 500번 서버에러

    }
}
