package com.jinsungwon99.user.ui;

import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.user.application.UserRelationService;
import com.jinsungwon99.user.application.dto.FollowUserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor //생성자
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    public Response<Void> followUser(@RequestBody FollowUserRequestDto dto){
        userRelationService.follow(dto);
        return Response.ok(null);
    }
}
