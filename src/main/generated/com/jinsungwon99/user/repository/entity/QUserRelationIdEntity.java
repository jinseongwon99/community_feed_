package com.jinsungwon99.user.repository.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRelationIdEntity is a Querydsl query type for UserRelationIdEntity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserRelationIdEntity extends BeanPath<UserRelationIdEntity> {

    private static final long serialVersionUID = -292857030L;

    public static final QUserRelationIdEntity userRelationIdEntity = new QUserRelationIdEntity("userRelationIdEntity");

    public final NumberPath<Long> followerUserId = createNumber("followerUserId", Long.class);

    public final NumberPath<Long> followingUserId = createNumber("followingUserId", Long.class);

    public QUserRelationIdEntity(String variable) {
        super(UserRelationIdEntity.class, forVariable(variable));
    }

    public QUserRelationIdEntity(Path<? extends UserRelationIdEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRelationIdEntity(PathMetadata metadata) {
        super(UserRelationIdEntity.class, metadata);
    }

}

