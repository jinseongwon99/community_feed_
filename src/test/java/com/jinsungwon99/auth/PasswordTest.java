package com.jinsungwon99.auth;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jinsungwon99.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class PasswordTest {

    //패스워드 확인 일치(정상)
    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue(){

        //given
        Password password = Password.createEncryptPassword("password");

        //when
        //then
        assertTrue(password.matchPassword("password"));
    }

    //패스워드 확인 불일치
    @Test
    void givenPassword_whenMatchDiffPassword_thenReturnFalse(){

        //given
        Password password = Password.createEncryptPassword("password");

        //when
        //then
        assertFalse(password.matchPassword("wrong password"));
    }

    //패스워드가 Null 일 때
    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenReturnFalse(String password){

        //given
        //then
        //then
        assertThrows(IllegalArgumentException.class,()->Password.createEncryptPassword(password));
    }
    
    
}
