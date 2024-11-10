package com.jinsungwon99.post.application.dto;

public record UpdateCommentRequestDto(Long userId, Long postId,String content) {

}
