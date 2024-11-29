package com.jinsungwon99.common.ui;

import com.jinsungwon99.common.domain.exception.ErrorCode;

public record Response<T>(Integer code, String message, T value) {

    public static <T> Response<T> ok(T value) {
        return new Response<>(0, "ok", value);
    }

    public static <T> Response<T> error(BaseException error) {
        return new Response<>(error.getCode(), error.getMessage(), null);
    }
}