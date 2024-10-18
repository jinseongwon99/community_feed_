package com.jinsungwon99.post.ui;

import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.post.application.CommentService;
import com.jinsungwon99.post.application.dto.CreateCommentRequestDto;
import com.jinsungwon99.post.application.dto.LikeCommentRequestDto;
import com.jinsungwon99.post.application.dto.UpdateCommentRequestDto;
import com.jinsungwon99.post.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Response<Long> createComment(@RequestBody CreateCommentRequestDto dto){
        Comment comment = commentService.createComment(dto);
        return Response.ok(comment.getId());
    }

    @PatchMapping("/{commentId}")
    public Response<Long> updateComment(@PathVariable(name = "commentId") Long commentId,
        @RequestBody UpdateCommentRequestDto dto){
        Comment comment = commentService.updateComment(commentId,dto);
        return Response.ok(comment.getId());
    }

    @GetMapping("/like/{commentId}/{userId}")
    public Response<Void> commentLike(@PathVariable(name = "commentId") Long commentId,
        @PathVariable(name = "userId") Long userId){

        commentService.likeComment(new LikeCommentRequestDto(userId,commentId));
        return Response.ok(null);
    }

    @GetMapping("/unlike/{commentId}/{userId}")
    public Response<Void> commentUnLike(@PathVariable(name = "commentId") Long commentId,
        @PathVariable(name = "userId") Long userId){

        commentService.unlikeComment(new LikeCommentRequestDto(userId,commentId));
        return Response.ok(null);
    }

}
