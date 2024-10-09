package com.jinsungwon99.user.repository.entity;

import com.jinsungwon99.common.domain.PositiveIntegerCounter;
import com.jinsungwon99.common.repository.entity.TimeBaseEntity;
import com.jinsungwon99.user.domain.User;
import com.jinsungwon99.user.domain.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "community_user")
@AllArgsConstructor //모든 생성자
@NoArgsConstructor  //매계변수가 없는 생성자
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // DB의 id 값으로 자동 증감키 설정
    private Long id;
    private String name;
    private String profileImageUrl;
    private Integer followingCount;
    private Integer followerCount;

    public  UserEntity(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.profileImageUrl = user.getProfileImage();
        this.followerCount = user.getFollowerCount();
        this.followingCount = user.getFollowingCount();;
    }

    public User toUser(){
        return User.builder()
            .id(id)
            .info(new UserInfo(name,profileImageUrl))
            .followerCount(new PositiveIntegerCounter(followerCount))
            .followingCount(new PositiveIntegerCounter(followingCount))
            .build();
    }

}