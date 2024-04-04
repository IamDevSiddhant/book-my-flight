package io.bookflight.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookflight.entity.UserInfo;
import io.bookflight.events.ProduceKafkaEvent;
import io.bookflight.repository.UserRepository;
import io.bookflight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interactions")
@Slf4j
public class UserController {


    private final UserService userService;
    private final ObjectMapper mapper;
    private final UserRepository repository;
    private final ApplicationEventPublisher publisher;

    public UserController(UserService userService, ObjectMapper mapper, UserRepository repository, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.mapper = mapper;
        this.repository = repository;
        this.publisher = publisher;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserInfo userInfo){
        ResponseEntity<?> saveUser=new ResponseEntity<>(userService.saveUser(userInfo),HttpStatus.CREATED);
        publisher.publishEvent(new ProduceKafkaEvent(userInfo,mapper));
        return saveUser;
    }

    @PutMapping("/update")
    public ResponseEntity<?> editUser(@RequestBody UserInfo userInfo, @RequestParam("id")String id){
        return new ResponseEntity<>(userService.editUser(userInfo, id),HttpStatus.OK);
    }

    @GetMapping("/getAll")

    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<?> getAllUsers(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key = "#id",value = "user")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        repository.deleteById(id);
        log.info("User Deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/email")
    public ResponseEntity<?> getByEmail(@RequestParam("userEmail")String userEmail){
        return new ResponseEntity<>(userService.getByEmail(userEmail),HttpStatus.OK);
    }

    @GetMapping("/phone")
    public ResponseEntity<?> getByPhone(@RequestParam("phoneNumber")String phoneNumber){
        return new ResponseEntity<>(userService.getByPhone(phoneNumber),HttpStatus.OK);
    }
}
