package com.jinsungwon99.post.application.Interfaces;

import com.jinsungwon99.post.domain.Post;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);

}
