package io.bookflight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookMyFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyFlightApplication.class, args);
	}

}
