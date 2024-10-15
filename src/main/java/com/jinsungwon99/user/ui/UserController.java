package com.jinsungwon99.user.ui;

import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.application.dto.GetUserListResponseDto;
import com.jinsungwon99.user.application.dto.GetUserResponseDto;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.repository.jpa.JpaUserListQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor //생성자
public class UserController {

    private final UserService userService;

    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto){
        User user = userService.createUser(dto);
        return Response.ok(user.getId());
    }

    @GetMapping("{userId}/follower")
    public Response<List<GetUserListResponseDto>> gerFollowerList(@PathVariable(name= "userId") Long userId){
        List<GetUserListResponseDto> userList = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(userList);
    }

    @GetMapping("{userId}/following")
    public Response<List<GetUserListResponseDto>> gerFollowingList(@PathVariable(name= "userId") Long userId){
        List<GetUserListResponseDto> userList = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(userList);
    }

    @GetMapping("{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId){
        GetUserResponseDto response= userService.getUserProfile(userId);
        return Response.ok(response);

    }
}
