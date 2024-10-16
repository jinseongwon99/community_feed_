package com.jinsungwon99.post.application;

import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.application.Interfaces.PostRepository;
import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.application.dto.LikePostRequestDto;
import com.jinsungwon99.post.application.dto.UpdatePostRequestDto;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.content.PostContent;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;
    private final PostRepository postRepository;
    private final LikePostRepository likePostRepository;

    public PostService(UserService userService, PostRepository postRepository,
        LikePostRepository likeRepository) {

        this.userService = userService;
        this.postRepository = postRepository;
        this.likePostRepository = likeRepository;
    }

    public Post getPost(Long id){
        return postRepository.findById(id);
    }

    public Post createPost(CreatePostRequestDto requestDto){
        User user = userService.getUser(requestDto.userid());
        PostContent content = new PostContent(requestDto.content());
        Post post = new Post(null,user,content,requestDto.state());

        return postRepository.save(post);
    }

    public Post updatePost(Long postId,UpdatePostRequestDto requestDto){
        User user = userService.getUser(requestDto.userid());
        Post post = getPost(postId);
        post.updatePost(user,requestDto.content(),requestDto.state());

        return postRepository.save(post);
    }

    public void likePost(LikePostRequestDto requestDto){
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if (likePostRepository.checkLike(user,post)){
            return;
        }
        post.like(user);
        likePostRepository.like(user,post);
    }

    public void unlikePost(LikePostRequestDto requestDto){
        User user = userService.getUser(requestDto.userId());
        Post post = getPost(requestDto.postId());

        if(likePostRepository.checkLike(user,post)){
            post.unlike();
            likePostRepository.unlike(user,post);
        }
    }
}

