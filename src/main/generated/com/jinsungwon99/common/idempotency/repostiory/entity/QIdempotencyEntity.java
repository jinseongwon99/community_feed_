package com.jinsungwon99.common.idempotency.repostiory.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIdempotencyEntity is a Querydsl query type for IdempotencyEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIdempotencyEntity extends EntityPathBase<IdempotencyEntity> {

    private static final long serialVersionUID = -1876992404L;

    public static final QIdempotencyEntity idempotencyEntity = new QIdempotencyEntity("idempotencyEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idempotencyKey = createString("idempotencyKey");

    public final StringPath response = createString("response");

    public QIdempotencyEntity(String variable) {
        super(IdempotencyEntity.class, forVariable(variable));
    }

    public QIdempotencyEntity(Path<? extends IdempotencyEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIdempotencyEntity(PathMetadata metadata) {
        super(IdempotencyEntity.class, metadata);
    }

}

