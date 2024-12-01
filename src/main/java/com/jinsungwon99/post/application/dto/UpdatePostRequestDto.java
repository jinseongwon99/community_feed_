package com.jinsungwon99.post.application.dto;

import com.jinsungwon99.post.domain.content.PostPublicationState;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state,
                                   @RequestParam(value = "contentImageUrl", required = false) MultipartFile contentImageUrl) {
}
