package com.jinsungwon99.common.idempotency.repostiory.jpa;

import com.jinsungwon99.common.idempotency.Idempotency;
import com.jinsungwon99.common.idempotency.repostiory.entity.IdempotencyEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaIdempotencyRepository extends JpaRepository<IdempotencyEntity,Long> {

    Optional<IdempotencyEntity> findByIdempotencyKey(String key);
}
