package com.jinsungwon99.common.idempotency;

import com.jinsungwon99.common.ui.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Idempotency {

    private final String key;
    private final Response<?> response;
}
