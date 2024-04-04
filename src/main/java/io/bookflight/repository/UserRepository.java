package io.bookflight.repository;

import io.bookflight.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface UserRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findByUserEmail(String email);
    Optional<UserInfo> findByPhoneNumber(String phone);
}
