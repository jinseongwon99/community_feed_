package com.jinsungwon99.auth.repository.entity;

import com.jinsungwon99.auth.domain.UserAuth;
import com.jinsungwon99.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "community_user_auth")
@Getter
public class UserAuthEntity extends TimeBaseEntity{

    @Id
    private String email;
    private String password;
    private String userRole;
    private Long userId;
    private LocalDateTime lastLoginDt;

    //userAuth -> Entity 변환
    public UserAuthEntity(UserAuth userAuth,Long userId){
        this.email = userAuth.getEmailText();
        this.password = userAuth.getPasswordText();
        this.userRole = userAuth.getUserRole();
        this.userId = userId;
    }

    //Entity -> userAuth 변환
    public UserAuth toUserAuth(){
        return new UserAuth(email,password,userRole,userId);
    }

    public void updateLastLoginAt(){
        lastLoginDt = LocalDateTime.now();
    }
}
