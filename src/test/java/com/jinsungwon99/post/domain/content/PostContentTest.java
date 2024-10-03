package com.jinsungwon99.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    private PostContent postContent;

    //빈값
    @Test
    void givenContentNull_whenCheckText_thenThrowError(){

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,()-> postContent =new PostContent(""));
    }
    //빈값
    @ParameterizedTest
    @NullAndEmptySource
    void givenContentNull_whenCheckText_thenThrowError(String value){

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,()-> postContent =new PostContent(value));
    }

    //글자수 5미만 일 경우 오류
    @Test
    void givenContentSizeUnderFive_whenCheckText_thenThrowError(){

        //given
        //when
        //then
        assertThrows(IllegalArgumentException.class,()-> postContent =new PostContent("5미만"));
    }

    //글자수 정상 5이상 500이하
    @Test
    void givenContentNormal_whenCheckText_thenThrowSuccess(){

        //given
        String content = "정상 포스트입니다";
        //when
        PostContent postContent = new PostContent(content);
        //then
        assertEquals(content,postContent.contentText);
    }

    //글자수 500 이상
    @Test
    void givenContentOver_whenCheckText_thenThrowError() {
        // given
        String content = "a".repeat(501); // 501자의 문자열 생성

        //repeat = String 반복

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "긁", "삵", "숡"})
    void givenContentOverKorea_whenCheckText_thenThrowError(String koreanWord) {
        // given
        String content = koreanWord.repeat(501); // 501자의 문자열 생성

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }



}