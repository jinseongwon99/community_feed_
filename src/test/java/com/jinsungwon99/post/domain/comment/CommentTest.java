package com.jinsungwon99.post.domain.comment;

import static org.junit.jupiter.api.Assertions.*;

import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.content.CommentContent;
import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

class CommentTest {

    private final UserInfo userInfo = new UserInfo("홍길동","");
    private final User user = new User(1L,userInfo);
    private final User user1 = new User(2L,userInfo);
    private final PostContent postContent = new PostContent("글작성했습니다");
    private final Post post = new Post(1L,user,postContent);


    //Comment 정상 작성
    @Test
    void givenCreateComment_whenCheck_thenSuccess(){

        //given
        String text = "댓글 작성";

        //when
        Comment comment =new Comment(1L,post,user,new CommentContent(text));

        //then
        assertEquals(text,comment.getContent());
        }

    //User Null
    @Test
    void givenCommentIsUserNull_whenCheck_thenThrowError(){

        //given
        String text = "댓글 작성";

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Comment(1L,post,null,new CommentContent(text)));
    }

    //Post Null
    @Test
    void givenCommentIsPostNull_whenCheck_thenThrowError(){

        //given
        String text = "댓글 작성";

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Comment(1L,null,user,new CommentContent(text)));
    }

    //Content Null
    @Test
    void givenCommentIsContentNull_whenCheck_thenThrowError(){

        //given
        String text = "댓글 작성";

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->new Comment(1L,post,user,null));
    }
    //Comment Like
    @Test
    void givenCreateComment_whenLike_thenLikeCountIsOne(){

        //given
        String text = "댓글 작성";
        Comment comment = new Comment(1L,post,user,new CommentContent(text));

        //when
        comment.like(user1);

        //then
        assertEquals(1,comment.getLikeCount());
    }

    //Comment Like And Same User
    @Test
    void givenCreateComment_whenLikeAndSameUser_thenThrowError(){

        //given
        String text = "댓글 작성";
        Comment comment = new Comment(1L,post,user,new CommentContent(text));

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->comment.like(user));
    }

    /*
        Comment Like And UnLike
     */
    @Test
    void givenCreateCommentAndLike_whenUnLike_thenLikeCountISZero() {
        // given
        String text = "댓글 작성";
        Comment comment = new Comment(1L, post, user, new CommentContent(text));
        comment.like(user1);

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    /*
        Comment NotLike And UnLike
     */
    @Test
    void givenCreateComment_whenUnLike_thenLikeCountISZero() {
        // given
        String text = "댓글 작성";
        Comment comment = new Comment(1L, post, user, new CommentContent(text));

        // when
        comment.unlike();

        // then
        assertEquals(0, comment.getLikeCount());
    }

    //Comment Update
    @Test
    void givenCreateComment_whenUpdateComment_thenSuccess(){

        //given
        String text = "댓글 작성";
        Comment comment = new Comment(1L,post,user,new CommentContent(text));

        //when
        String updateText = "수정합니다.";
        comment.updateComment(user,updateText);

        //then
        assertEquals(updateText,comment.getContent());
    }

    //Comment Update And different User
    @Test
    void givenCreateComment_whenUpdateCommentAndDiffUser_thenThrowError(){

        //given
        String text = "댓글 작성";
        Comment comment = new Comment(1L,post,user,new CommentContent(text));

        //when
        String updateText = "수정합니다.";


        //then
        assertThrows(IllegalArgumentException.class,()->comment.updateComment(user1,updateText));
    }
}