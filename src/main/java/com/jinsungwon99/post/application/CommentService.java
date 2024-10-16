package com.jinsungwon99.post.application;

import com.jinsungwon99.post.application.Interfaces.CommentRepository;
import com.jinsungwon99.post.application.Interfaces.LikeCommentRepository;
import com.jinsungwon99.post.application.dto.CreateCommentRequestDto;
import com.jinsungwon99.post.application.dto.LikeCommentRequestDto;
import com.jinsungwon99.post.application.dto.UpdateCommentRequestDto;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final LikeCommentRepository likeCommentRepository;

    public CommentService(UserService userService, CommentRepository commentRepository,
        PostService postService, LikeCommentRepository likeCommentRepository) {
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.likeCommentRepository = likeCommentRepository;
    }

    public Comment getComment(Long id){
        return commentRepository.findById(id);
    }

    public Comment createComment(CreateCommentRequestDto requestDto){
        User user = userService.getUser(requestDto.userId());
        Post post = postService.getPost(requestDto.postId());
        
        //정적 생성자로 생성
        Comment comment = Comment.createComment(null,post,user,requestDto.content());

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId,UpdateCommentRequestDto requestDto){
        Comment comment = getComment(commentId);
        User user = userService.getUser(requestDto.userId());

        comment.updateComment(user, requestDto.content());

        return commentRepository.save(comment);
    }

    public void likeComment(LikeCommentRequestDto requestDto){
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user,comment)){
            return;
        }
        comment.like(user);
        likeCommentRepository.like(user,comment);
    }

    public void unlikeComment(LikeCommentRequestDto requestDto){
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user,comment)){
            comment.unlike();
            likeCommentRepository.unlike(user,comment);
        }
    }
}
