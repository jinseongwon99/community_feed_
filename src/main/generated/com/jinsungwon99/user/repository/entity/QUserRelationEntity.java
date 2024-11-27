package com.jinsungwon99.user.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRelationEntity is a Querydsl query type for UserRelationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRelationEntity extends EntityPathBase<UserRelationEntity> {

    private static final long serialVersionUID = -524302017L;

    public static final QUserRelationEntity userRelationEntity = new QUserRelationEntity("userRelationEntity");

    public final com.jinsungwon99.common.repository.entity.QTimeBaseEntity _super = new com.jinsungwon99.common.repository.entity.QTimeBaseEntity(this);

    public final NumberPath<Long> followerUserId = createNumber("followerUserId", Long.class);

    public final NumberPath<Long> followingUserId = createNumber("followingUserId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updDt = _super.updDt;

    public QUserRelationEntity(String variable) {
        super(UserRelationEntity.class, forVariable(variable));
    }

    public QUserRelationEntity(Path<? extends UserRelationEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRelationEntity(PathMetadata metadata) {
        super(UserRelationEntity.class, metadata);
    }

}

