package io.bookflight.service;

import io.bookflight.entity.BookFlight;
import io.bookflight.entity.FlightDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public interface FlightService {
    BookFlight saveFlightBooking(String airLine, String departureCity, String destination, String userId, LocalDate bookingDate);

    BookFlight updateBookedFlight(long id, BookFlight bookFlight);

    List<BookFlight> getAllBookings();

    BookFlight getById(long id);

    List<BookFlight> getBookingDetails(String departCity,String destination,String userId);

    FlightDetails saveFlightDetails(String airLine, String departureCity, String destination, LocalDate bookingDate,Long ticketPrice);

    FlightDetails updateFlightDetails(long flightId,FlightDetails flightDetails);

    List<FlightDetails> getAllFlights();

    List<FlightDetails> getFlightDetails(String departCity,String destination,LocalDate date);



}
