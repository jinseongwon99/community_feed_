package com.jinsungwon99.common.application;

import com.jinsungwon99.common.domain.exception.ErrorCode;
import com.jinsungwon99.common.repository.NotificationRepository;
import com.jinsungwon99.common.repository.entity.NotificationEntity;
import com.jinsungwon99.common.ui.BaseException;
import com.jinsungwon99.common.ui.dto.PostSaveNotificationRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    // 알림 저장
    @Transactional
    public void saveNotification(PostSaveNotificationRequestDto dto) {
        NotificationEntity entity = new NotificationEntity(dto.userId(), dto.body(), dto.contentUrl(), false);
        notificationRepository.save(entity);
    }

    // 읽지 않은 내 알림 조회
    public List<NotificationEntity> getAllMyNotifications(Long userId) {
        return notificationRepository.findAllByUserIdAndIsReadFalse(userId);
    }

    // 알림 읽음 처리
    @Transactional
    public void markAsRead(Long id) {
        NotificationEntity entity = findById(id);
        entity.updateIsRead();

        notificationRepository.save(entity);
    }

    // 알림 조회
    public NotificationEntity findById(Long id) {
        NotificationEntity entity = notificationRepository.findById(id)
            .orElseThrow(() -> new BaseException(ErrorCode.NOT_FOUND));

        return entity;
    }

}