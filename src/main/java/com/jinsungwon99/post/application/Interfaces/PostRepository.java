package com.jinsungwon99.post.application.Interfaces;

import com.jinsungwon99.post.domain.Post;
import java.util.List;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);

    List<Post> findAllByUserIdOrderByIdDesc(Long userId);
}
