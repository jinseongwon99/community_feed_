package com.jinsungwon99.post.ui.dto;

import com.jinsungwon99.post.domain.Post;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostMainResponseDto {

    private Post post;
    private List<GetContentResponseDto> comment;
    private Long userId;
    private Long authUserId;

}
