package com.jinsungwon99.post.ui;

import com.jinsungwon99.common.idempotency.Idempotent;
import com.jinsungwon99.common.principal.AuthPrincipal;
import com.jinsungwon99.common.principal.UserPrincipal;
import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.post.application.Interfaces.LikeCommentRepository;
import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.application.PostService;
import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.application.dto.LikePostRequestDto;
import com.jinsungwon99.post.application.dto.UpdatePostRequestDto;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.repository.post_queue.UserPostQueueQueryRepository;
import com.jinsungwon99.post.ui.dto.GetContentResponseDto;
import com.jinsungwon99.post.ui.dto.GetPostMainResponseDto;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.domain.User;
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

    private final UserService userService;
    private final PostService postService;
    private final UserPostQueueQueryRepository userPostQueueQueryRepository;
    private final LikePostRepository likePostRepository;
    private final LikeCommentRepository likeCommentRepository;

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
    @GetMapping("/like/{postId}")
    public Response<Integer> postLike(@PathVariable(name = "postId") Long postId,
        @AuthPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getUserId(); // 로그인한 사용자 ID
        int likeCount = postService.likePost(new LikePostRequestDto(userId, postId));

        return Response.ok(likeCount);
    }



    @GetMapping("/unlike/{postId}")
    public Response<Integer> postUnLike(@PathVariable(name = "postId") Long postId,
        @AuthPrincipal UserPrincipal userPrincipal){
        Long userId = userPrincipal.getUserId(); // 로그인한 사용자 ID
        int likeCount = postService.unlikePost(new LikePostRequestDto(userId,postId));
        return Response.ok(likeCount);
    }

    @GetMapping("/getPost/{postId}")
    public Response<GetPostMainResponseDto> post(@AuthPrincipal UserPrincipal userPrincipal,
        @PathVariable(name = "postId") Long postId) {

        Long userId = userPrincipal.getUserId(); // 로그인한 사용자 ID
        Post post = postService.getPost(postId); // 게시물 조회
        List<GetContentResponseDto> comment = userPostQueueQueryRepository.getCommentResponse(postId, post.getAuthorId(), 0L); // 댓글 조회

        User user = userService.getUser(userId);

        // 현재 사용자가 좋아요를 눌렀는지 확인
        boolean isLiked = likePostRepository.checkLike(user, post); // 좋아요 상태 체크

        // GetPostMainResponseDto 객체 생성, isLiked 값 추가
        GetPostMainResponseDto result = new GetPostMainResponseDto(post, comment, userId, post.getAuthorId(), isLiked);

        return Response.ok(result); // 응답 반환
    }
}
