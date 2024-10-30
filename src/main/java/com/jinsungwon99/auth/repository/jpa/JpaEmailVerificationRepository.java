package com.jinsungwon99.auth.repository.jpa;

import com.jinsungwon99.auth.repository.entity.EmailVerificationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity,Long> {

    Optional<EmailVerificationEntity> findByEmail(String email);
}
