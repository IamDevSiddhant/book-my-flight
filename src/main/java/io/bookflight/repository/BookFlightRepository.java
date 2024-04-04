package io.bookflight.repository;

import io.bookflight.entity.BookFlight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookFlightRepository extends JpaRepository<BookFlight,Long> {

    Optional<List<BookFlight>>findByDepartureCityAndDestinationAndUserId(String departureCity, String destination, String userId);

}
