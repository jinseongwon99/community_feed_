package com.jinsungwon99.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jinsungwon99.auth.domain.TokenProvider;
import org.junit.jupiter.api.Test;

class TokenProviderTest {


    private final String  secretKey = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    private final TokenProvider tokenProvider = new TokenProvider(secretKey);

    @Test
    void givenValidUserAndRole_whenCreateToken_thenReturnValidToken() {

        //given
        Long userId = 1L;
        String role = "ADMIN";
        //when
        String token = tokenProvider.createToken(userId, role);

        //then
        Long testUserId = tokenProvider.getUserId(token);
        String testRole = tokenProvider.getUserRole(token);

        assertEquals(userId, testUserId);
        assertEquals(testRole, role);

    }

    @Test
    void givenInValidToken_whenGetUserId_thenThrowError() {

        //given
        //when
        String token = "";

        //then
       assertThrows(Exception.class,()-> tokenProvider.getUserId(token));
    }
}