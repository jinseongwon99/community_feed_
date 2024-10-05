package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.Post;

public record CreateCommentRequestDto(Long postId,Long userId,String content) {

}
