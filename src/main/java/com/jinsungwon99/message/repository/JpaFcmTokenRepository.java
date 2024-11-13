package com.jinsungwon99.message.repository;

import com.jinsungwon99.message.repository.entity.FcmTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaFcmTokenRepository extends JpaRepository<FcmTokenEntity,Long> {

}
