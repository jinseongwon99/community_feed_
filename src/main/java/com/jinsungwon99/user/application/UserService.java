package com.jinsungwon99.user.application;

import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.application.interfaces.UserRepository;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(CreateUserRequestDto dto){
        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null,userInfo);

        return userRepository.save(user);
    }
    public User getUser(Long id){
        return userRepository.findById(id);
    }
}
