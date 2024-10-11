package com.jinsungwon99.user.repository;

import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.domain.User;
import java.util.HashMap;
import java.util.Map;

public class FakeUserRepository implements UserRepository {

    private final Map<Long,User> store = new HashMap<>();

    @Override
    public User save(User user) {
        if(user.getId() != null){
            store.put(user.getId(), user);
        }
        Long id = store.size() + 1L;
        User newUser = new User(id,user.getInfo());
        store.put(id,newUser);
        return newUser;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }
}
