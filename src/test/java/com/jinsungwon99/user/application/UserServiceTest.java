package com.jinsungwon99.user.application;

import static org.junit.jupiter.api.Assertions.*;

import com.jinsungwon99.fake.FakeObjectFactory;
import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser(){

        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("홍길동", "www.naver.com");

        //when
        User user1 = userService.createUser(dto);

        //then
        User user2 = userService.getUser(user1.getId());
        UserInfo userInfo = user2 .getInfo();

        assertEquals(user1.getId(),user2.getId());
        assertEquals("홍길동",userInfo.getName());
    }
}