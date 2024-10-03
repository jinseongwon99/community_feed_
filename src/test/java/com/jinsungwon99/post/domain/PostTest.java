package com.jinsungwon99.post.domain;

import static org.junit.jupiter.api.Assertions.*;

import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.post.domain.content.PostPublicatuionState;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostTest {

    private final UserInfo userInfo = new UserInfo("홍길동","");
    private Post post1;
    private PostContent postContent;
    private final User user1 = new User(1L,userInfo);
    private final User user2 = new User(2L,userInfo);

    @BeforeEach
    void init() {
        postContent = new PostContent("글작성합니다");
        post1 = new Post(1L, user1, postContent);

    }

    /*
       좋아요
    */
    @Test
    void givenCreateUserAndPost_whenLike_thenCountIsOne(){
        //given
        //when
        post1.like(user2);

        //then
        assertEquals(1,post1.getLikeCount());
    }
    /*
        자기 가진 글 좋아요 에러
     */

    @Test
    void givenCreatePost_whenLikeMyself_thenThrowError(){
        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,()-> post1.like(user1));
    }

    /*
       싫어요
    */
    @Test
    void givenCreatePostAndLike_whenUnLike_thenCountIsZero(){

        //given
        post1.like(user2);
        //when
        post1.unlike();
        //then
        assertEquals(0,post1.getLikeCount());
    }

    /*
        업데이트
    */
    @Test
    void givenCreatePost_whenUpdate_thenSave(){
        //given
        //when
        post1.updatePost(user1,"수정했습니다", PostPublicatuionState.PUBLIC);

        //then
        assertEquals("수정했습니다", post1.getContent());
        assertEquals(PostPublicatuionState.PUBLIC, post1.getState());
    }
}