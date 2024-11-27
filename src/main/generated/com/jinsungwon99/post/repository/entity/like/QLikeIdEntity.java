package com.jinsungwon99.post.repository.entity.like;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLikeIdEntity is a Querydsl query type for LikeIdEntity
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLikeIdEntity extends BeanPath<LikeIdEntity> {

    private static final long serialVersionUID = -1950846228L;

    public static final QLikeIdEntity likeIdEntity = new QLikeIdEntity("likeIdEntity");

    public final NumberPath<Long> targetId = createNumber("targetId", Long.class);

    public final StringPath targetType = createString("targetType");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QLikeIdEntity(String variable) {
        super(LikeIdEntity.class, forVariable(variable));
    }

    public QLikeIdEntity(Path<? extends LikeIdEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLikeIdEntity(PathMetadata metadata) {
        super(LikeIdEntity.class, metadata);
    }

}

