package com.jinsungwon99.acceptance.steps;

import com.jinsungwon99.auth.application.dto.SendEmailRequestDto;
import io.restassured.RestAssured;
import org.springframework.http.MediaType;

public class SignUpAcceptanceSteps {

    public static Integer requestSendEmail(SendEmailRequestDto dto){
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/signup/send-verification-email")
            .then()
            .extract()
            .jsonPath().get("code");    //response 의 code 를 가져옴
    }
}
