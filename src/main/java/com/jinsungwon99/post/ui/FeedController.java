package com.jinsungwon99.post.ui;

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

    @GetMapping("/{userId}")
    public Response<List<GetPostContentResponseDto>> getPostFeed(@PathVariable(name = "userId") Long userId,Long lastPostId){
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(userId,lastPostId);
        return Response.ok(result);
    }

    @GetMapping("/comment/{userId}/{postId}")
    public Response<List<GetContentResponseDto>> getCommentResponse(@PathVariable(name = "postId") Long postId,Long lastCommentId,
                                                                    @PathVariable(name = "userId") Long userId){
        List<GetContentResponseDto> result = userPostQueueQueryRepository.getCommentResponse(postId,lastCommentId,userId);
        return Response.ok(result);
    }
}
