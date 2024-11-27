package com.jinsungwon99.common.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeBaseEntity is a Querydsl query type for TimeBaseEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QTimeBaseEntity extends EntityPathBase<TimeBaseEntity> {

    private static final long serialVersionUID = -1423940106L;

    public static final QTimeBaseEntity timeBaseEntity = new QTimeBaseEntity("timeBaseEntity");

    public final DateTimePath<java.time.LocalDateTime> regDt = createDateTime("regDt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updDt = createDateTime("updDt", java.time.LocalDateTime.class);

    public QTimeBaseEntity(String variable) {
        super(TimeBaseEntity.class, forVariable(variable));
    }

    public QTimeBaseEntity(Path<? extends TimeBaseEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeBaseEntity(PathMetadata metadata) {
        super(TimeBaseEntity.class, metadata);
    }

}

