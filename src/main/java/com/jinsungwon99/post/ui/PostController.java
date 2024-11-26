package com.jinsungwon99.post.ui;

import com.jinsungwon99.common.idempotency.Idempotent;
import com.jinsungwon99.common.principal.AuthPrincipal;
import com.jinsungwon99.common.principal.UserPrincipal;
import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.post.application.PostService;
import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.application.dto.LikePostRequestDto;
import com.jinsungwon99.post.application.dto.UpdatePostRequestDto;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.repository.post_queue.UserPostQueueQueryRepository;
import com.jinsungwon99.post.ui.dto.GetContentResponseDto;
import com.jinsungwon99.post.ui.dto.GetPostMainResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserPostQueueQueryRepository userPostQueueQueryRepository;

    @PostMapping
    public Response<Long> createPost(@ModelAttribute CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,
        @RequestBody UpdatePostRequestDto dto){
        Post post = postService.updatePost(postId,dto);
        return Response.ok(post.getId());
    }
    @Idempotent
    @GetMapping("/like/{postId}/{userId}")
    public Response<Integer> postLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId) {
        System.out.println("Attempting to like postId: " + postId + " by userId: " + userId);
        int likeCount = postService.likePost(new LikePostRequestDto(userId, postId));

        return Response.ok(likeCount);
    }


    @GetMapping("/unlike/{postId}/{userId}")
    public Response<Integer> postUnLike(@PathVariable(name = "postId") Long postId,
        @PathVariable(name = "userId") Long userId){

        int likeCount = postService.unlikePost(new LikePostRequestDto(userId,postId));
        return Response.ok(null);
    }

    @GetMapping("/getPost/{postId}")
    public Response<GetPostMainResponseDto> post(@AuthPrincipal UserPrincipal userPrincipal,
                                                 @PathVariable(name = "postId") Long postId) {

        Long usrId = userPrincipal.getUserId();
        Post post = postService.getPost(postId);
        List<GetContentResponseDto> comment = userPostQueueQueryRepository.getCommentResponse(postId, post.getAuthorId(),0L);

        GetPostMainResponseDto result = new GetPostMainResponseDto(post,comment,usrId,post.getAuthorId());

        return Response.ok(result);
    }



}
