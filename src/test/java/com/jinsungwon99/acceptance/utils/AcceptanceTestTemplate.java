package com.jinsungwon99.acceptance.utils;

import static com.jinsungwon99.acceptance.steps.LoginAcceptanceSteps.requestLoginGetToken;

import com.jinsungwon99.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

// API 테스트 코드 캡슐화 (중복 코드 제거 및 재사용성 향상)
// DB 초기화 및 샘플 데이터 주입
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) // 기본 8080 포트로 서버 연결
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader loader;

    @BeforeEach
    public void init(){
        cleanUp.execute(); // DB 초기화, ID값 초기화
        loader.loadData(); // 샘플 데이터 주입
    }

    protected void cleanUp(){
        cleanUp.execute();
    }

    protected String getEmailToken(String email){
        return loader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email){
        return loader.isEmailVerified(email);
    }
    protected Long getUserId(String email){
        return loader.getUserId(email);
    }
    protected void createUser(String email){
        loader.createUser(email);
    }
    protected String login(String email){
        return requestLoginGetToken(new LoginRequestDto(email,"password"));
    }
}
