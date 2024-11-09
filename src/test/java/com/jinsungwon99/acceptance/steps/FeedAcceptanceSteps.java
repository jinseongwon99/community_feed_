package com.jinsungwon99.acceptance.steps;

import com.jinsungwon99.post.application.dto.CreatePostRequestDto;
import com.jinsungwon99.post.ui.dto.GetPostContentResponseDto;
import io.restassured.RestAssured;
import java.util.List;
import org.springframework.http.MediaType;

// import 주의!
// RestAssured 라이브러리를 이용한 API 테스트
public class FeedAcceptanceSteps {

    //create Post
    public static Long reqCreatePost(CreatePostRequestDto dto){
        return RestAssured
            .given().log().all()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/post")
            .then().log().all()
            .extract()
            // Long 반환하기
            .jsonPath()
            .getObject("value", Long.class);

    }
    // feed 조회하기
    public static List<GetPostContentResponseDto> requestFeed(String token){
        return RestAssured
            .given().log().all()
            .header("Authorization","Bearer " + token)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/feed")
            .then().log().all()
            .extract()
            .jsonPath()
            .getList("value",GetPostContentResponseDto.class);

    }
    //Feed 조회 응답코드 조회
    public static Integer requestFeedCode(String token){
        return RestAssured
            .given().log().all()
            .header("Authorization","Bearer " + token)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/feed")
            .then().log().all()
            .extract()
            .jsonPath()
            .get("code");

    }
}
