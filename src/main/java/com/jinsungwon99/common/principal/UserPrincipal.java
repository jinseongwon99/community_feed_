package com.jinsungwon99.common.principal;

import com.jinsungwon99.auth.domain.UserRole;
import lombok.Getter;

// 커스텀_어노테이션의 return 값
@Getter
public class UserPrincipal {

    private Long userId;
    private UserRole userRole;

    public UserPrincipal(Long userId, String role) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(role);
    }
}
