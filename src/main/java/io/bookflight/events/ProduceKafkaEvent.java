package io.bookflight.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bookflight.entity.UserInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@Slf4j
public class ProduceKafkaEvent extends ApplicationEvent {
    private UserInfo userInfo;
    private final ObjectMapper mapper;
    public ProduceKafkaEvent(UserInfo userInfo, ObjectMapper mapper) {
        super(userInfo);
        this.userInfo=userInfo;
        this.mapper = mapper;
        try {
            log.info("Producing Kafka Event "+mapper.writeValueAsString(userInfo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
