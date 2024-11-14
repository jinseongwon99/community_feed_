package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.content.PostPublicationState;
import org.springframework.web.multipart.MultipartFile;

public record CreatePostRequestDto(Long userId, String content, MultipartFile contentImageUrl, PostPublicationState state) {

}
