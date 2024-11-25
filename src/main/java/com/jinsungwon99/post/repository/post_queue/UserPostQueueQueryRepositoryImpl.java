package com.jinsungwon99.post.repository.post_queue;

import com.jinsungwon99.post.repository.entity.comment.QCommentEntity;
import com.jinsungwon99.post.repository.entity.like.QLikeEntity;
import com.jinsungwon99.post.repository.entity.post.QPostEntity;
import com.jinsungwon99.post.repository.entity.post.QUserPostQueueEntity;
import com.jinsungwon99.post.ui.dto.GetContentResponseDto;
import com.jinsungwon99.post.ui.dto.GetPostContentResponseDto;
import com.jinsungwon99.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile({"!test"})
@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository{
    private final JPAQueryFactory queryFactory;

    // Q객체 불러오기
    private static final QUserPostQueueEntity userPostQueueEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity postEntity = QPostEntity.postEntity;
    private static final QUserEntity userEntity = QUserEntity.userEntity;
    private static final QLikeEntity likeEntity = QLikeEntity.likeEntity;
    private static final QCommentEntity commentEntity = QCommentEntity.commentEntity;

    //유저의 피드값 가져오기
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastContentId) {
        return queryFactory
            .select(
                Projections.fields( //DTO에 맞춰ㅏ서 데이터 출력 명시
                    GetPostContentResponseDto.class, //DTO
                    postEntity.id.as("id"),     //DTO의 필드명에 맞추기 위해 as 지정(= id)
                    postEntity.content.as("content"),
                    userEntity.id.as("userId"),
                    userEntity.name.as("userName"),
                    userEntity.profileImageUrl.as("userProfileImageUrl"),
                    postEntity.regDt.as("createdAt"),
                    postEntity.updDt.as("updatedAt"),
                    postEntity.commentCount.as("commentCount"),
                    postEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe") //left join 조건에 만족하면 true 아니면 false
                )
            )
            .from(userPostQueueEntity)
            .join(postEntity).on(userPostQueueEntity.postId.eq(postEntity.id))
            .join(userEntity).on(userPostQueueEntity.authorId.eq(userEntity.id))
            .leftJoin(likeEntity).on(hasLike(userId))
            //일단 left 테이블은 모두 가져옴(userPostQueueEntity) + 아래의 hasLike함수 조건의 결과값을 같이 출력
            .where(
                userPostQueueEntity.userId.eq(userId),
                hasLastData(lastContentId) //마지막 게시글보다 작은 값일 경우
            )
            .orderBy(userPostQueueEntity.postId.desc())
            .limit(20)
            .fetch();
    }

    //피드 댓글 가져오기
    public List<GetContentResponseDto> getCommentResponse(Long postId,Long userId,Long lastCommentId) {
        System.out.println("Fetching comments for postId: " + postId + ", userId: " + userId + ", lastCommentId: " + lastCommentId);
        return queryFactory
            .select(
                Projections.fields( //DTO에 맞춰서 데이터 출력 명시
                    GetContentResponseDto.class, //DTO
                    commentEntity.id.as("id"),     //DTO의 필드명에 맞추기 위해 as 지정(= id)
                    commentEntity.content.as("content"),
                    commentEntity.author.id.as("userId"),
                    commentEntity.author.name.as("userName"),
                    commentEntity.author.profileImageUrl.as("userProfileImageUrl"),
                    commentEntity.regDt.as("createdAt"),
                    commentEntity.updDt.as("updatedAt"),
                    commentEntity.likeCount.as("likeCount"),
                    likeEntity.isNotNull().as("isLikedByMe") //left join 조건에 만족하면 true 아니면 false
                )
            )
            .from(commentEntity)
            .leftJoin(likeEntity).on(hasCommentLike(userId))
            //일단 left 테이블은 모두 가져옴(userPostQueueEntity) + 아래의 hasLike함수 조건의 결과값을 같이 출력
            .where(
                commentEntity.post.id.eq(postId),
                hasCommentLastData(lastCommentId) //마지막 게시글보다 작은 값일 경우
            )
            .orderBy(commentEntity.id.desc())
            .limit(20)
            .fetch();

    }

    private BooleanExpression hasLastData(Long lastId) {
        if (lastId == null) {
            return null;
        }

        return postEntity.id.lt(lastId); //lastId보다 작을 때
    }

    private BooleanExpression hasLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return postEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("POST"))
            .and(likeEntity.id.userId.eq(userId));
    }

    private BooleanExpression hasCommentLastData(Long lastId) {
        if (lastId == null || lastId <= 0) {
            return null;  // 처음부터 모든 댓글을 가져오도록 처리
        }

        return commentEntity.id.lt(lastId); //lastId보다 작을 때
    }

    private BooleanExpression hasCommentLike(Long userId) {
        if (userId == null) {
            return null;
        }

        return commentEntity.id
            .eq(likeEntity.id.targetId)
            .and(likeEntity.id.targetType.eq("COMMENT"))
            .and(likeEntity.id.userId.eq(userId));
    }
}
