package com.jinsungwon99.message.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.signature.qual.IdentifierOrPrimitiveType;

@Entity
@Table(name = "community_fcm_token")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FcmTokenEntity {

    @Id
    private Long userId;
    private String fcmToken;
}
