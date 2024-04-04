package io.bookflight.entity;


import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = -4439114469417994311L;

    @Id
    private String  id;
    private String userName;
    private String userEmail;
    private String phoneNumber;
}
