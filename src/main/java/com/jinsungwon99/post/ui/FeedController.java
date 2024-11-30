package com.jinsungwon99.post.ui;

import com.jinsungwon99.common.principal.AuthPrincipal;
import com.jinsungwon99.common.principal.UserPrincipal;
import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.application.PostService;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.repository.post_queue.UserPostQueueQueryRepository;
import com.jinsungwon99.post.ui.dto.GetContentResponseDto;
import com.jinsungwon99.post.ui.dto.GetPostContentResponseDto;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
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
   private final LikePostRepository likePostRepository;
   private final UserService userService;
   private final PostService postService;
   private final PostRepository postRepository;

    @GetMapping
    public Response<List<GetPostContentResponseDto>> getPostFeed(@AuthPrincipal UserPrincipal userPrincipal) {
        Long lastPostId = 10L;

        // 사용자 정보 가져오기
        Long userId = userPrincipal.getUserId();

        // 게시글 리스트 가져오기
        List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getContentResponse(userId, lastPostId);

        // 좋아요 여부 설정
        result.forEach(postDto -> {
            // 게시글에 해당하는 Post 엔티티 가져오기
            Post postEntity = postRepository.findById(postDto.getId());

            // 좋아요 여부 확인 및 설정
            boolean isLiked = likePostRepository.checkLike(userPrincipal.toUser(), postEntity);
            postDto.setLikedByMe(isLiked);

            // contentImageUrl 설정
            postDto.setContentImageUrl(postEntity.getContentImageUrl());
        });

        return Response.ok(result);
    }



    @GetMapping("/comment/{postId}")
    public Response<List<GetContentResponseDto>> getCommentResponse(
        @PathVariable(name = "postId") Long postId,
        Long lastCommentId,
        @AuthPrincipal UserPrincipal userPrincipal) {

        List<GetContentResponseDto> result = userPostQueueQueryRepository.getCommentResponse(postId, lastCommentId, userPrincipal.getUserId());

        // 데이터 구조 확인 (예: result가 List<GetContentResponseDto>인지 확인)
        System.out.println("Comments for post " + postId + ": " + result);

        return Response.ok(result);
    }

}
