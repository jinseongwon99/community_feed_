package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.Post;

public record UpdateCommentRequestDto(Long commentId,Long userId, Long postId,String content) {

}
