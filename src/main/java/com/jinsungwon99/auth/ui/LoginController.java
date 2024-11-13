package com.jinsungwon99.auth.ui;


import com.jinsungwon99.auth.application.AuthService;
import com.jinsungwon99.auth.application.dto.LoginRequestDto;
import com.jinsungwon99.auth.application.dto.UserAccessTokenResponseDto;
import com.jinsungwon99.auth.domain.TokenProvider;
import com.jinsungwon99.common.domain.exception.ErrorCode;
import com.jinsungwon99.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto dto){
        System.out.println("==========="+dto.fcmToken());

        return Response.ok(authService.login(dto));
    }

    // Response 사용하여 userId 반환
    @GetMapping("/getUserId")
    public Response<Long> getUserId(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);  // "Bearer " 제거

            // 토큰 유효성 검증
            Long id = tokenProvider.getUserId(token);

            if (id != null) {
                return Response.ok(id);  // userId를 Response로 반환
            }
        }
        return Response.error(ErrorCode.INTERNAL_ERROR);  // Authorization 헤더가 없을 때
    }
}
