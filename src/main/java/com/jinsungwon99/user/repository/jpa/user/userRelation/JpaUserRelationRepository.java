package com.jinsungwon99.user.repository.jpa.user.userRelation;

import com.jinsungwon99.user.repository.entity.UserRelationEntity;
import com.jinsungwon99.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRelationRepository extends JpaRepository<UserRelationEntity, UserRelationIdEntity> {

}
