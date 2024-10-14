package com.jinsungwon99.common.ui;

import com.jinsungwon99.common.domain.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j //로그 기능
@RestControllerAdvice //예외를 처리와 관련된 기능 제공
public class GlobalExceptionHandler {
    
    //IllegalArgumentException 의 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public Response<Void> handleIllegalArgumentException(){
        return Response.error(ErrorCode.INVALID_INPUT_VALUE);
    }
    
    // 위의 IllegalArgumentException 을 제외한 나머지 예외 처리
    @ExceptionHandler(Exception.class)
    public Response<Void> handleException(Exception exception){
        log.error(exception.getMessage());
        return Response.error(ErrorCode.INVALID_INPUT_VALUE);
    }
}
