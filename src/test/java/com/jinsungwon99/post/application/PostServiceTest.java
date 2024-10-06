package com.jinsungwon99.post.application;

import static org.junit.jupiter.api.Assertions.*;

import com.jinsungwon99.fake.FakeObjectFactory;
import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.application.dto.LikePostRequestDto;
import com.jinsungwon99.post.application.dto.UpdatePostRequestDto;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.content.PostPublicationState;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.domain.User;
import org.junit.jupiter.api.Test;

class PostServiceTest {

    private final PostService postService = FakeObjectFactory.getPostService();
    private final UserService userService = FakeObjectFactory.getUserService();

    private final CreateUserRequestDto userDto = new CreateUserRequestDto("고길동","");

    private final User user = userService.createUser(userDto);
    private final User otherUser = userService.createUser(userDto);

    private final CreatePostRequestDto postDto = new CreatePostRequestDto(user.getId(),"글 작성합니다", PostPublicationState.PUBLIC);


    //CreatePost 테스트
    @Test
    void givenCreatePostAndCreateUser_whenSave_thenSuccess(){

        //given
        Post savePost = postService.createPost(postDto);
        //when
        Post newPost = postService.getPost(savePost.getId());
        //then
        assertEquals(savePost,newPost);
    }

    //getPost 테스트
    @Test
    void givenCreatePost_whenGetPost_thenSame(){

        //given
        Post post = postService.createPost(postDto);

        //when
        Post getPost = postService.getPost(post.getId());

        //then
        assertEquals(post.getId(),getPost.getId());
        assertEquals("글 작성합니다",getPost.getContent());

    }

    //UpdatePost 테스트
    @Test
    void givenCreatePost_whenUpdate_thenSuccess(){

        //given
        Post post = postService.createPost(postDto);
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(post.getId(),post.getAuthorId(),"수정합니다",post.getState());
        //when
        postService.updatePost(updateDto);

       assertEquals(updateDto.content(),post.getContent());
    }

    //LikePost 테스트 other User
    @Test
    void givenCreatePost_whenLike_thenCountIsOne(){

        //given
        Post post = postService.createPost(postDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(otherUser.getId(),post.getId());

        //when
        postService.likePost(likePostRequestDto);
        //then
        assertEquals(1,post.getLikeCount());
    }

    //LikePost 테스트 same User
    @Test
    void givenCreatePost_whenLikeAndSameUser_thenThrowError(){

        //given
        Post post = postService.createPost(postDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(user.getId(),post.getId());

        //when
        //then
        assertThrows(IllegalArgumentException.class,()->postService.likePost(likePostRequestDto));
    }

    //Like After UnLikePost 테스트
    @Test
    void  givenCreatePost_whenLikeAfterUnLike_thenSuccess(){

        //given
        Post post = postService.createPost(postDto);
        LikePostRequestDto likePostRequestDto = new LikePostRequestDto(otherUser.getId(),post.getId());

        //when
        postService.likePost(likePostRequestDto);
        postService.unlikePost(likePostRequestDto);
        //then
        assertEquals(0,post.getLikeCount());
    }


}