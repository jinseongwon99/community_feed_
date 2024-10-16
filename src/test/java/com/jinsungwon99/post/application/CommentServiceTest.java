package com.jinsungwon99.post.application;

import static org.junit.jupiter.api.Assertions.*;

import com.jinsungwon99.fake.FakeObjectFactory;
import com.jinsungwon99.post.application.dto.CreateCommentRequestDto;
import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.application.dto.LikeCommentRequestDto;
import com.jinsungwon99.post.application.dto.UpdateCommentRequestDto;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.post.domain.content.PostPublicationState;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.domain.User;
import org.junit.jupiter.api.Test;

class CommentServiceTest {

    private final CommentService commentService = FakeObjectFactory.getCommentService();
    private final PostService postService = FakeObjectFactory.getPostService();
    private final UserService userService = FakeObjectFactory.getUserService();

    private final CreateUserRequestDto userDto = new CreateUserRequestDto("홍길동","");
    private final User user = userService.createUser(userDto);
    private final User otherUser = userService.createUser(userDto);

    private final CreatePostRequestDto postDto = new CreatePostRequestDto(user.getId(),"글 작성합니다",
        PostPublicationState.PUBLIC);
    private final Post post = postService.createPost(postDto);


    //createComment
    @Test
    void givenCreateComment_whenSave_thenSuccess(){

        //given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(),user.getId(),"댓글입니다.");

        //when
        Comment saveComment = commentService.createComment(dto);

        //then
        assertEquals(dto.content(),saveComment.getContent());
    }

    //updateComment
    @Test
    void givenCreateComment_whenUpdate_thenSuccess(){

        //given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(),user.getId(),"댓글입니다.");
        Comment comment = commentService.createComment(dto);

        //when
        UpdateCommentRequestDto updateDto = new UpdateCommentRequestDto(comment.getAuthorId(),comment.getPostId(),"업데이트합니다");
        Comment updateComment = commentService.updateComment(comment.getId(), updateDto);

        //then
        assertEquals(updateDto.content(),updateComment.getContent());
    }

    //LikeComment
    @Test
    void givenCreateComment_whenLike_thenLikeCountIsOne(){

        //given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(),user.getId(),"댓글입니다.");
        Comment comment = commentService.createComment(dto);

        //when
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(otherUser.getId(),comment.getId());
        commentService.likeComment(likeDto);

        //then
        assertEquals(1,comment.getLikeCount());

    }

    //LikeComment & Same User
    @Test
    void givenCreateComment_whenLikeButSameUser_thenThrowError(){

        //given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(),user.getId(),"댓글입니다.");
        Comment comment = commentService.createComment(dto);

        //when
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(user.getId(),comment.getId());

        //then
        assertThrows(IllegalArgumentException.class,()->commentService.likeComment(likeDto));

    }

    //unLikeComment
    @Test
    void givenCreateCommentAndLike_whenUnLike_thenLikeCountIsOne(){

        //given
        CreateCommentRequestDto dto = new CreateCommentRequestDto(post.getId(),user.getId(),"댓글입니다.");
        Comment comment = commentService.createComment(dto);
        LikeCommentRequestDto likeDto = new LikeCommentRequestDto(otherUser.getId(),comment.getId());
        commentService.likeComment(likeDto);

        //when
        commentService.unlikeComment(likeDto);

        //then
        assertEquals(0,comment.getLikeCount());

    }



}