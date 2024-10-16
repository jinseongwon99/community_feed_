package com.jinsungwon99.post.application.dto;

public record CreateCommentRequestDto(Long postId,Long userId,String content) {

}
