package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.Post;

public record UpdateCommentRequestDto(Long userId, Long postId,String content) {

}
