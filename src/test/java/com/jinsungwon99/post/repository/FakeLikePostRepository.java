package com.jinsungwon99.post.repository;

import com.jinsungwon99.post.application.Interfaces.LikePostRepository;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.user.domain.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FakeLikePostRepository implements LikePostRepository {

    private final Map<Post,Set<User>> store = new HashMap<>();


    @Override
    public boolean checkLike(User user, Post post) {
        if(store.get(post) == null){
            return false;
        }

        return store.get(post).contains(user);
    }

    @Override
    public void like(User user, Post post) {
        Set<User> users = store.get(post);
        if(users == null){
            users = Set.of(user);
        }else{
            users.add(user);
        }
    }

    @Override
    public void unlike(User user, Post post) {
       Set<User>users =store.get(post);
       if(user == null){
           return;
       }
       users.remove(user);
       store.put(post,users);
    }
}
