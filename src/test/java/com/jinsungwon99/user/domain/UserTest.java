package com.jinsungwon99.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo = new UserInfo("홍길동","www.naver.com");
    private User user1;
    private User user2;

    @BeforeEach
    void init(){
        user1 = new User(1L , userInfo);
        user2 = new User(2L , userInfo);
    }

    @Test
    void givenTwoUser_whenEquals_thenReturnFalse(){

        //when
        boolean value = user1.equals(user2);

        //then
        assertFalse(value);
    }

    @Test
    void givenSameIdUser_whenEquals_thenReturnTrue(){
        //given
        User user3 = new User(1L,userInfo);

        //when
        boolean value = user1.equals(user3);

        //then
        assertTrue(value);
    }

    @Test
    void givenSameIdUser_whenHashCode_thenNotEquals(){

        //when
        int hashcode1 = user1.hashCode();
        int hashcode2 = user2.hashCode();

        //then
        assertNotEquals(hashcode1,hashcode2);
    }

    @Test
    void givenSameIdUser_whenHashCode_thenEquals(){

        //given
        User user3 = new User(1L,userInfo);

        //when
        int hashcode1 = user1.hashCode();
        int hashcode3 = user3.hashCode();

        //then
        assertEquals(hashcode1,hashcode3);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount(){

        //given

        //when
        user1.follow(user2);

        //then
        assertEquals(1,user1.getFollowingCount());
        assertEquals(0,user1.getFollowerCount());
        assertEquals(0,user2.getFollowingCount());
        assertEquals(1,user2.getFollowerCount());
    }

    @Test
    void givenTwoUserUser1FollowUser2_whenUnFollower_thenDecreaseUserCount(){

        //given
        user1.follow(user2);

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0,user1.getFollowingCount());
        assertEquals(0,user1.getFollowerCount());
        assertEquals(0,user2.getFollowingCount());
        assertEquals(0,user2.getFollowerCount());
    }

    @Test
    void givenTwoUser_whenUnFollower_thenCountIsZero(){

        //when
        user1.unfollow(user2);

        //then
        assertEquals(0,user1.getFollowingCount());
        assertEquals(0,user1.getFollowerCount());
        assertEquals(0,user2.getFollowingCount());
        assertEquals(0,user2.getFollowerCount());
    }
}