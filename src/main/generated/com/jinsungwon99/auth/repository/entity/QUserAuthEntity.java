package com.jinsungwon99.auth.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserAuthEntity is a Querydsl query type for UserAuthEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAuthEntity extends EntityPathBase<UserAuthEntity> {

    private static final long serialVersionUID = -378873426L;

    public static final QUserAuthEntity userAuthEntity = new QUserAuthEntity("userAuthEntity");

    public final com.jinsungwon99.common.repository.entity.QTimeBaseEntity _super = new com.jinsungwon99.common.repository.entity.QTimeBaseEntity(this);

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> lastLoginDt = createDateTime("lastLoginDt", java.time.LocalDateTime.class);

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final StringPath userRole = createString("userRole");

    public QUserAuthEntity(String variable) {
        super(UserAuthEntity.class, forVariable(variable));
    }

    public QUserAuthEntity(Path<? extends UserAuthEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserAuthEntity(PathMetadata metadata) {
        super(UserAuthEntity.class, metadata);
    }

}

