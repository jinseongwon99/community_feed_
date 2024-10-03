package com.jinsungwon99.post.domain.content;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

    private CommentContent commentContentent;

    //Comment null

    @ParameterizedTest
    @NullAndEmptySource
    void givenCommentIsNull_whenCheck_thenThrowError(String value){

        //given
        assertThrows(IllegalArgumentException.class,()-> new CommentContent(value));
    }


    //Comment 100 over
    @ParameterizedTest
    @ValueSource(strings = {"뷁","닭","슭"})
    void givenCommentIsOver_whenCheck_thenThrowError(String value){

        //given
        String text = value.repeat(101);
        assertThrows(IllegalArgumentException.class,()-> new CommentContent(text));
    }

    //Comment 정상
    @Test
    void givenCommentIsNormal_whenCheck_thenSuccess(){

        //given
        String text = "정상";

        //when
        CommentContent commentContent = new CommentContent(text);

        //then
        assertEquals(text,commentContent.getContentText());
    }

}