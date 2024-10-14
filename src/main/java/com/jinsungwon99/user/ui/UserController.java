package com.jinsungwon99.user.ui;

import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor //생성자
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto){
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }
}
