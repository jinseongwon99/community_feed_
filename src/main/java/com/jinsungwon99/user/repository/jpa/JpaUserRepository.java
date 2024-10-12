package com.jinsungwon99.user.repository.jpa;

import com.jinsungwon99.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> { //<가지고 와야할 Entity, 그 값의 Id>

}
