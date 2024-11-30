package com.jinsungwon99.user.ui;

import com.jinsungwon99.common.principal.AuthPrincipal;
import com.jinsungwon99.common.principal.UserPrincipal;
import com.jinsungwon99.common.ui.Response;
import com.jinsungwon99.post.application.PostService;
import com.jinsungwon99.post.domain.Post;
import com.jinsungwon99.user.application.UserRelationService;
import com.jinsungwon99.user.application.UserService;
import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.application.dto.GetUserListResponseDto;
import com.jinsungwon99.user.application.dto.GetUserResponseDto;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.repository.jpa.JpaUserListQueryRepository;
import com.jinsungwon99.user.ui.dto.GetProfileResponseDto;
import com.jinsungwon99.user.ui.dto.PatchPasswordRequestDto;
import com.jinsungwon99.user.ui.dto.PatchProfileImageRequestDto;
import com.jinsungwon99.user.ui.dto.PatchProfileRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final UserRelationService userRelationService;
    private final JpaUserListQueryRepository jpaUserListQueryRepository;

//    @PostMapping
//    public Response<Long> createUser(@RequestBody CreateUserRequestDto dto) {
//        User user = userService.createUser(dto);
//
//        return Response.ok(user.getId());
//    }

    @GetMapping("/{userId}/follower")
    public Response<List<GetUserListResponseDto>> getFollowerList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> userList = jpaUserListQueryRepository.getFollowerUserList(userId);
        return Response.ok(userList);
    }

    @GetMapping("/{userId}/following")
    public Response<List<GetUserListResponseDto>> getFollowingList(@PathVariable(name = "userId") Long userId) {
        List<GetUserListResponseDto> userList = jpaUserListQueryRepository.getFollowingUserList(userId);
        return Response.ok(userList);
    }

    @GetMapping("/{userId}")
    public Response<GetUserResponseDto> getUserProfile(@PathVariable(name = "userId") Long userId) {
        GetUserResponseDto response = userService.getUserProfile(userId);
        return Response.ok(response);
    }

    /*
        프로필 정보만 조회
     */
    @GetMapping("/getProfileData")
    public Response<GetUserResponseDto> getUserProfileData(@AuthPrincipal UserPrincipal userPrincipal) {
        Long userId = userPrincipal.getUserId();
        GetUserResponseDto response = userService.getUserProfile(userId);

        return Response.ok(response);
    }

    /*
        프로필 조회
     */
    @GetMapping("/getProfile/{otherUserId}")
    public Response<GetProfileResponseDto> profile(@AuthPrincipal UserPrincipal userPrincipal,
        @PathVariable(name = "otherUserId") Long otherUserId) {

        // 내 userId 불러오기
        Long userId = userPrincipal.getUserId();

        // otherUserId가 null 이거나 0이면, 현재 로그인한 사용자(userId)의 프로필로 처리
        if (otherUserId == null || otherUserId == 0L) {
            otherUserId = userId;
        }

        // 구독 여부 확인
        boolean isFollowing = false;

        if (!otherUserId.equals(userId)) {
            isFollowing = userRelationService.isAlreadyFollow(userId, otherUserId);
        }

        // 해당 계정의 포스트 가져오기
        List<Post> postList = postService.getMyPostList(otherUserId);

        // 해당 계정의 프로필 정보 가져오기
        GetUserResponseDto profile = userService.getUserProfile(otherUserId);

        GetProfileResponseDto result = new GetProfileResponseDto(userId, otherUserId, postList, profile, isFollowing);

        return Response.ok(result);
    }

    @GetMapping("/getUserId")
    public Response<Long> getUserId(@AuthPrincipal UserPrincipal userPrincipal) {

        Long userId = userPrincipal.getUserId();

        return Response.ok(userId);
    }

    /*
        프로필 사진 등록
     */
    @PatchMapping("/upload/profileImage")
    public Response<Void> uploadProfileImage(@AuthPrincipal UserPrincipal userPrincipal,
        @ModelAttribute PatchProfileImageRequestDto dto) {

        Long userId = userPrincipal.getUserId();

        userService.patchProfileImage(userId, dto);

        return Response.ok(null);
    }

    /*
        비밀번호 변경
     */
    @PatchMapping("/changePassword")
    public Response<Void> changePassword(@AuthPrincipal UserPrincipal userPrincipal,
        @RequestBody PatchPasswordRequestDto dto) {

        Long userId = userPrincipal.getUserId();

        userService.changePassword(userId, dto.getPassword());

        return Response.ok(null);
    }

    /*
        사용자 이름 변경
     */
    @PatchMapping("/changeProfile")
    public Response<Void> changeProfile(@AuthPrincipal UserPrincipal userPrincipal,
        @RequestBody PatchProfileRequestDto dto) {

        Long userId = userPrincipal.getUserId();

        userService.changeProfile(userId, dto.getName());

        return Response.ok(null);
    }

}
