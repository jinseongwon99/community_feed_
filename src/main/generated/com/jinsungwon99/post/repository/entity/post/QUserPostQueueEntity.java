package com.jinsungwon99.post.repository.entity.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPostQueueEntity is a Querydsl query type for UserPostQueueEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPostQueueEntity extends EntityPathBase<UserPostQueueEntity> {

    private static final long serialVersionUID = -855342693L;

    public static final QUserPostQueueEntity userPostQueueEntity = new QUserPostQueueEntity("userPostQueueEntity");

    public final NumberPath<Long> authorId = createNumber("authorId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserPostQueueEntity(String variable) {
        super(UserPostQueueEntity.class, forVariable(variable));
    }

    public QUserPostQueueEntity(Path<? extends UserPostQueueEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPostQueueEntity(PathMetadata metadata) {
        super(UserPostQueueEntity.class, metadata);
    }

}

