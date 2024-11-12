package com.jinsungwon99.common.idempotency.repostiory;

import com.jinsungwon99.common.idempotency.Idempotency;
import com.jinsungwon99.common.idempotency.IdempotencyRepository;
import com.jinsungwon99.common.idempotency.repostiory.entity.IdempotencyEntity;
import com.jinsungwon99.common.idempotency.repostiory.jpa.JpaIdempotencyRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IdempotencyRepositoryImpl implements IdempotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        Optional<IdempotencyEntity> idempotencyEntity = jpaIdempotencyRepository.findByIdempotencyKey(key);

        return idempotencyEntity.map(IdempotencyEntity::toIdempotency).orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
