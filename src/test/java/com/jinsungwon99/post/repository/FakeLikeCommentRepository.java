package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.LikeCommentRepository;
import com.jinsungwon99.post.domain.comment.Comment;
import com.jinsungwon99.user.domain.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FakeLikeCommentRepository implements LikeCommentRepository {

    private final Map<Comment, Set<User>>  store = new HashMap<>();

    @Override
    public boolean checkLike(User user, Comment comment) {
        if(store.get(comment) == null){
            return false;
        }
        return true;
    }

    @Override
    public void like(User user, Comment comment) {
        Set<User> users = store.get(comment);
        if(users == null){
            users = new HashSet<>();
        }
        users.add(user);
        store.put(comment,users);
    }

    @Override
    public void unlike(User user, Comment comment) {
        Set<User> users = store.get(comment);
        if(users == null) {
            return;
        }
        users.remove(user);
        store.put(comment,users);
    }
}
