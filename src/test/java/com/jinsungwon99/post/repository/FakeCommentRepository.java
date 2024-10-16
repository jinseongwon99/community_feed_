package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.CommentRepository;
import com.jinsungwon99.post.domain.comment.Comment;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeCommentRepository implements CommentRepository {

    private final Map<Long, Comment> store = new HashMap<>();


    @Override
    public Comment save(Comment comment) {
        if(comment.getId() != null){
            store.put(comment.getId(),comment);
        }
        long commentId = store.size() + 1 ;
        Comment newComment = new Comment(commentId,comment.getPost(),comment.getAuthor(),comment.getContentObject());
        store.put(commentId,newComment);
        return newComment;
    }

    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }
}
