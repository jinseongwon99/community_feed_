package com.jinsungwon99.acceptance.auth;

import static com.jinsungwon99.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static com.jinsungwon99.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(400,code); //HTTP 400번 client 에러

    }

    //정상 이메일, 토큰 확인
    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified(){

        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email,token);

        //then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(0,code);
        assertTrue(isEmailVerified);


    }
    //정상 이메일, 잘못된 토큰 일 때
    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNOtVerified(){

        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail(email,"wrong token");

        //then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400,code);
        assertFalse(isEmailVerified);
    }

    //인증이 완료된 이메일에 재인증 시도 할 때
    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError(){

        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email,token);

        //when
        Integer code = requestVerifyEmail(email,token);

        //then
        assertEquals(400,code);

    }

    //인증할 이메일이 잘못될 때
    @Test
    void givenSendEmail_whenVerifyEmailWithWrongEmail_thenThrowError(){

        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail("wrong email","token");

        //then
        assertEquals(400,code);
    }
}
