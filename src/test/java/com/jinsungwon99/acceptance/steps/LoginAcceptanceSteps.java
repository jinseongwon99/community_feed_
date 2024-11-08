package com.jinsungwon99.acceptance.steps;

import com.jinsungwon99.auth.application.dto.LoginRequestDto;
import com.jinsungwon99.auth.application.dto.UserAccessTokenResponseDto;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    public static String requestLoginGetToken(LoginRequestDto dto){
        UserAccessTokenResponseDto res = RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("login")
            .then()
            .extract()
            .jsonPath()
            .getObject("value", UserAccessTokenResponseDto.class);

        return res.accessToken();
    }

    public static Integer requestLoginGetResponseCode(LoginRequestDto dto){
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("login")
            .then()
            .extract()
            .jsonPath()
            .getObject("code", Integer.class);

    }



}
