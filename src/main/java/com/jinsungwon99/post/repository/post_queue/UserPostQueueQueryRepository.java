package com.jinsungwon99.post.repository.post_queue;

import com.jinsungwon99.post.ui.dto.GetPostContentResponseDto;
import java.util.List;

public interface UserPostQueueQueryRepository {

    List<GetPostContentResponseDto> getContentResponse(Long userId,Long lastPostId);
}
