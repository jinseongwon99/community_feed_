package com.jinsungwon99.acceptance.auth;

import static com.jinsungwon99.acceptance.steps.LoginAcceptanceSteps.requestLoginGetResponseCode;
import static com.jinsungwon99.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jinsungwon99.acceptance.utils.AcceptanceTestTemplate;
import com.jinsungwon99.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class LoginAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@gmail.com";

    @BeforeEach
    void setUp(){
        this.cleanUp(); //데이터 초기화
        this.createUser(email); //유저 생성
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken(){

        //given
        LoginRequestDto dto = new LoginRequestDto(email,"password","fcmToken");

        //when
        String token = requestLoginGetToken(dto);

        //then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero(){

        //given
        LoginRequestDto dto = new LoginRequestDto(email,"Wrong password","fcmToken");

        //when
        Integer code = requestLoginGetResponseCode(dto);

        //then
        assertEquals(400,code);
    }
}
