package io.bookflight.repository;

import io.bookflight.entity.FlightDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightDetailsRepository extends JpaRepository<FlightDetails, Long> {
    Optional<List<FlightDetails>> findByDepartureCityAndDestinationAndAvailableDate(String departureCity, String destination
            , LocalDate date);
}
