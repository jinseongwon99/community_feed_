package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.content.PostPublicationState;

public record CreatePostRequestDto(Long userId,String content,PostPublicationState state) {

}
