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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Comment updateComment(Long commentId,UpdateCommentRequestDto requestDto){
        Comment comment = getComment(commentId);
        User user = userService.getUser(requestDto.userId());

        comment.updateComment(user, requestDto.content());

        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        // 게시글 조회
        Comment comment = getComment(commentId);

        // 작성자 확인
        if (!comment.getAuthor().getId().equals(userId)) {
            throw new IllegalArgumentException("작성자와 사용자가 다릅니다. 삭제할 수 없습니다.");
        }

        // 게시글 삭제
        commentRepository.delete(comment);
    }

    @Transactional
    public void deleteAllByPostId(Long postId) {

        // 게시글 삭제
        commentRepository.deleteAllByPostId(postId);
    }

    public int likeComment(LikeCommentRequestDto requestDto){
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user,comment)){
            return comment.getLikeCount();
        }
        comment.like(user);
        likeCommentRepository.like(user,comment);
        return comment.getLikeCount();
    }

    public int unlikeComment(LikeCommentRequestDto requestDto){
        Comment comment = getComment(requestDto.commentId());
        User user = userService.getUser(requestDto.userId());

        if (likeCommentRepository.checkLike(user,comment)){
            comment.unlike();
            likeCommentRepository.unlike(user,comment);
            return comment.getLikeCount();
        }
        return comment.getLikeCount();
    }
}
