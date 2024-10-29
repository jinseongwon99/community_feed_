package com.jinsungwon99.acceptance.utils;

import static com.jinsungwon99.acceptance.steps.UserAcceptanceSteps.createUser;
import static com.jinsungwon99.acceptance.steps.UserAcceptanceSteps.followUser;

import com.jinsungwon99.user.application.dto.CreateUserRequestDto;
import com.jinsungwon99.user.application.dto.FollowUserRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

// 샘플데이터 생성 
@Component
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    public void loadData(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test user", "");
        //테스 API 호출
        createUser(dto);
        createUser(dto);
        createUser(dto);

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(1L, 3L));
    }

    public String getEmailToken(String email){
        return entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
            .setParameter(1, email)
            .getSingleResult()
            .toString();
    }
}
