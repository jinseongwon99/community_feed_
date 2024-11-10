package com.jinsungwon99.admin.repository;

import com.jinsungwon99.admin.ui.dto.GetTableListResponse;
import com.jinsungwon99.admin.ui.dto.users.GetUserTableRequestDto;
import com.jinsungwon99.admin.ui.dto.users.GetUserTableResponseDto;
import com.jinsungwon99.admin.ui.query.AdminTableQueryRepository;
import com.jinsungwon99.auth.repository.entity.QUserAuthEntity;
import com.jinsungwon99.user.repository.entity.QUserEntity;
import com.mysql.cj.x.protobuf.MysqlxCrud.Projection;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminTableQueryRepositoryImpl implements AdminTableQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QUserAuthEntity userAuthEntity = QUserAuthEntity.userAuthEntity;
    private final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public GetTableListResponse<GetUserTableResponseDto> getUserTableData(
        GetUserTableRequestDto dto) {

        int total = jpaQueryFactory.select(userEntity.id)
            .from(userEntity)
            .where(likeName(dto.getName()))
            .fetch()
            .size();

        List<GetUserTableResponseDto> result = jpaQueryFactory
            .select(
                Projections.fields(
                    GetUserTableResponseDto.class,
                    userEntity.id.as("id"),
                    userAuthEntity.email.as("email"),
                    userEntity.name.as("name"),
                    userAuthEntity.userRole.as("role"),
                    userEntity.regDt.as("createdAt"),
                    userEntity.updDt.as("updatedAt"),
                    userAuthEntity.lastLoginDt.as("lastLoginAt")
                )
            )
            .from(userEntity)
            .join(userAuthEntity).on(userAuthEntity.userId.eq(userEntity.id))
            .where(likeName(dto.getName()))
            .orderBy(userEntity.id.desc())
            .offset(dto.getOffset())
            .limit(dto.getLimit())
            .fetch();

        return new GetTableListResponse<>(total,result);
    }

    public BooleanExpression likeName(String name){
        if(name == null || name.isBlank()){
            return null;
        }
        return userEntity.name.like( "%" + name + "%");
    }
}
