package io.bookflight.service;


import io.bookflight.entity.UserInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    UserInfo saveUser(UserInfo user);
    UserInfo editUser(UserInfo user, String id);


    List<UserInfo> getAllUsers();

    UserInfo getUserById(String id);

    UserInfo getByEmail(String email);
    UserInfo getByPhone(String phoneNumber);


}
