package com.jinsungwon99.message.application.interfaces;

import com.jinsungwon99.user.domain.User;

// 좋아요를 눌렀을 때 메시지 전송
public interface MessageRepository {

    void sendLikeMessage(User sender, User targetUser);
}
