package io.bookflight.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class IdUtils {

    public long setRandomLongId(){
        log.info("setting random id ");
        long id= 0L;
        Random random = new Random();
        id = random.nextInt(900000)+100000;
        return id;
    }

}
