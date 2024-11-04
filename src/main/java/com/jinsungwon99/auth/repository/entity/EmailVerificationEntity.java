package com.jinsungwon99.auth.repository.entity;

import com.jinsungwon99.common.repository.entity.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_email_verification")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailVerificationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String token;
    private boolean isVerified;

    public EmailVerificationEntity(String email, String token) {
        this.email = email;
        this.token = token;
        this.isVerified = false;
    }

    public boolean isVerified(){
        return isVerified;
    }

    //새로운 토큰으로 변경
    public void updateToken(String token){
        this.token = token;
    }
    
    //인증 true
    public void verify(){
        this.isVerified = true;
    }

    //동일 토큰 확인
    public boolean hasSaveToken(String token){
        return this.token.equals(token);
    }
}
