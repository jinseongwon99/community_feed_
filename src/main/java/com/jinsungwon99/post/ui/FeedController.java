package com.jinsungwon99.post.ui;

import com.jinsungwon99.common.principal.AuthPrincipal;
import com.jinsungwon99.common.principal.UserPrincipal;
import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.post.repository.post_queue.UserPostQueueQueryRepository;
import com.jinsungwon99.post.ui.dto.GetContentResponseDto;
import com.jinsungwon99.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

   private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @GetMapping("")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal, Long lastPostId){
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(userPrincipal.getUserId(),lastPostId);
        return Response.ok(result);
    }

    @GetMapping("/comment/{postId}")
    public Response<List<GetContentResponseDto>> getCommentResponse(@PathVariable(name = "postId") Long postId,Long lastCommentId,
                                                                   @AuthPrincipal UserPrincipal userPrincipal){
        List<GetContentResponseDto> result = userPostQueueQueryRepository.getCommentResponse(postId,lastCommentId,userPrincipal.getUserId());
        return Response.ok(result);
    }
}
