package com.jinsungwon99.common.idempotency;

public interface IdempotencyRepository {

    Idempotency getByKey(String key);
    void save(Idempotency idempotency);
}
