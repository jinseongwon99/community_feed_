package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.domain.Post;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakePostRepository implements PostRepository {

    private final Map<Long,Post> store = new HashMap<>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null){
            store.put(post.getId(), post);
            return post;
        }
        long postid = store.size() + 1 ;
        Post newPost = new Post(postid,post.getAuthor(),post.getContentObject());
        store.put(postid,newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Post> findAllByUserIdOrderByIdDesc(Long userId) {
        return List.of();
    }
}
