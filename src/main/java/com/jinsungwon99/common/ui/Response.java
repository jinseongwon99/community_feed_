package com.jinsungwon99.common.ui;

import com.jinsungwon99.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {
    //T는 특정한 타입으로 제한되지 않고, 사용자가 어떤 타입이든 사용할 수 있습니다.
    public static <T> Response<T> ok (T value){
        return new Response<>(0,"ok",value);
    }

    public static <T> Response<T> error (ErrorCode error){
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}
