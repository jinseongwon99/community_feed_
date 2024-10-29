package com.jinsungwon99.auth.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {

    //이메일 없을때
    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email){
        assertThrows(IllegalArgumentException.class, ()-> Email.createEmail(email));
    }

    //유효하지 않은 이메일 일때
    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com","natty#@naver","안녕@하세요.com"})
    void givenInvalidEmailValid_whenCreate_thenRThrowError(String email){
        assertThrows(IllegalArgumentException.class, ()-> Email.createEmail(email));
    }

    //정상 이메일
    @ParameterizedTest
    @ValueSource(strings = {"vaild@ab", "a@nvaer.com","natty@naver","test@Test.com"})
    void givenEmailValid_whenCreate_thenReturnEmail(String email){

        //given

        //when
        Email emailValue = Email.createEmail(email);

        //then
        assertEquals(email, emailValue.getEmailText());
    }



}