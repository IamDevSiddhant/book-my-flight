package io.bookflight.model;

import jakarta.persistence.Id;


import org.springframework.data.redis.core.RedisHash;


import java.io.Serializable;


@RedisHash("user")
public class UserInfoRedis implements Serializable {

    @Id
    private String  id;
    private String userName;
    private String userEmail;
    private String phoneNumber;
}
