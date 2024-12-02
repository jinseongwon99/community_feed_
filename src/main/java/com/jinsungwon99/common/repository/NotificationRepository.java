package com.jinsungwon99.common.repository;

import com.jinsungwon99.common.repository.entity.NotificationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByIsReadFalse();
    List<NotificationEntity> findAllByUserIdAndIsReadFalse(Long userId);
}