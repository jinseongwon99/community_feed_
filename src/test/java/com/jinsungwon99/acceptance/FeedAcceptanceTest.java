package com.jinsungwon99.acceptance;

import static com.jinsungwon99.acceptance.steps.FeedAcceptanceSteps.reqCreatePost;
import static com.jinsungwon99.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jinsungwon99.acceptance.utils.AcceptanceTestTemplate;
import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.domain.content.PostPublicationState;
import com.jinsungwon99.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RequiredArgsConstructor
class FeedAcceptanceTest extends AcceptanceTestTemplate {


     /*
      *  User 1 --> follow --> User 2
      *
      *  User 1 --> follow --> User 3
      **/

    @BeforeEach
     void setUp(){
        super.init();
    }

    /*
     *  User 2 create Post 1
     *
     *  User 1 get post 1 form feed
     **/

    @Test
    void givenUser2CreatePost1_whenUser1getPost1_thenCheck(){

        //given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L,"유저 2가 작성합니다",PostPublicationState.PUBLIC);
        Long createPostId= reqCreatePost(dto);

        //when
        List<GetPostContentResponseDto> result = requestFeed(1L);

        //then
        assertEquals(1,result.size());
        assertEquals(createPostId,result.get(0).getId());

    }
}
