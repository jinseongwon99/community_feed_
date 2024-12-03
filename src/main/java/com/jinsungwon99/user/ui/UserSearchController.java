package com.jinsungwon99.user.ui;

import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.ui.dto.GetSearchByUserNameResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/search")
@RequiredArgsConstructor
public class UserSearchController {

    private final UserService userService;

    @GetMapping("/{userName}")
    public Response<List<GetSearchByUserNameResponseDto>> searchByUserName(@PathVariable("userName") String userName) {

        List<User> userList = userService.findByLikeUserName(userName);

        List<GetSearchByUserNameResponseDto> result = userList.stream()
            .map(user -> new GetSearchByUserNameResponseDto(user.getId(), user.getName(), user.getProfileImageUrl())).toList();

        return Response.ok(result);
    }
}