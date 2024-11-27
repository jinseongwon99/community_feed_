package com.jinsungwon99.message.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFcmTokenEntity is a Querydsl query type for FcmTokenEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFcmTokenEntity extends EntityPathBase<FcmTokenEntity> {

    private static final long serialVersionUID = 1836250735L;

    public static final QFcmTokenEntity fcmTokenEntity = new QFcmTokenEntity("fcmTokenEntity");

    public final StringPath fcmToken = createString("fcmToken");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QFcmTokenEntity(String variable) {
        super(FcmTokenEntity.class, forVariable(variable));
    }

    public QFcmTokenEntity(Path<? extends FcmTokenEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFcmTokenEntity(PathMetadata metadata) {
        super(FcmTokenEntity.class, metadata);
    }

}

