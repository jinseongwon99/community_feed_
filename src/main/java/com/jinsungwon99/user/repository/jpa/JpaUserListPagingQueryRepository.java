package com.jinsungwon99.user.repository.jpa;

import com.jinsungwon99.user.application.dto.GetUserListResponseDto;
import com.jinsungwon99.user.repository.entity.QUserEntity;
import com.jinsungwon99.user.repository.entity.QUserRelationEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollowerList(Long userId,Long lastFollowerId){
        return jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserListResponseDto.class
                )
            )
            .from(relation)
            .join(user).on(relation.followingUserId.eq(user.id))
            .where(relation.followerUserId.eq(userId),
                    hasLastData(lastFollowerId))    //데이터 중복 방지
            .orderBy(user.id.desc())
            .limit(20)
            .fetch();
    }
    //페이징 처리에서 전페이지 데이터 중복 방지
    private BooleanExpression hasLastData(Long lastId){
        if (lastId == null){
            return null;
        }
        //lt(값) : 값보다 작은 값
        //gt(값) : 값보다 높은 값
        return user.id.lt(lastId);
    }
}
