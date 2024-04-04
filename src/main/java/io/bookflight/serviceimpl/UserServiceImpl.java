package io.bookflight.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookflight.entity.UserInfo;
import io.bookflight.exceptionhandler.UserExceptions;
import io.bookflight.repository.UserRepository;
import io.bookflight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;

    public UserServiceImpl(UserRepository userRepository, ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public UserInfo saveUser(UserInfo user) {
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override

    public UserInfo editUser(UserInfo user, String id) {
        List<UserInfo> usersList = userRepository.findAll();
        UserInfo userInfo = usersList.stream()
                .filter(userid -> {
                    String usersId = userid.getId();
                    return usersId.equalsIgnoreCase(id);
                })
                .findFirst()
                .map(modifiedUser -> {
                    modifiedUser.setUserEmail(user.getUserEmail());
                    modifiedUser.setUserName(user.getUserName());
                    modifiedUser.setPhoneNumber(user.getPhoneNumber());
                    userRepository.save(modifiedUser);
                    return modifiedUser;
                }).orElseThrow(()->new UserExceptions("No User Found",404));

        try {
            log.info("Optional user info after updation is {} ",mapper.writeValueAsString(userInfo));

        } catch (JsonProcessingException e) {
            log.info("Error while parsing json {} ",e);
        }
        return userInfo;

    }

    @Override
    @Cacheable(value = "user")
    public List<UserInfo> getAllUsers() {
        log.info("calling get all method -> from db");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(key = "#id",value = "user")
    public UserInfo getUserById(String id) {
        log.info("calling get by id method -> from db");
        return userRepository.findById(id).orElseThrow(()->new UserExceptions("User Not Found",404));
    }

    @Override
    @Cacheable(value = "usersByEmail", key = "#email", unless = "#result == null")
    public UserInfo getByEmail(String email) {
        log.info("calling get by email method -> from db");
        return userRepository.findByUserEmail(email)
                .orElseThrow(()->new UserExceptions("User Not Found",404));
    }

    @Override
    @Cacheable(value = "usersByPhone", key = "#phone", unless = "#result == null")
    public UserInfo getByPhone(String phone) {
        log.info("calling get by phone method -> from db");
        return userRepository.findByPhoneNumber(phone)
                .orElseThrow(()->new UserExceptions("User Not Found",404));
    }
}
