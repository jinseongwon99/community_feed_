package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long postId,Long userid, String content, PostPublicationState state) {

}
