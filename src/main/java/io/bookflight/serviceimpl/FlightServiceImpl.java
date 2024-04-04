package io.bookflight.serviceimpl;

import io.bookflight.entity.BookFlight;
import io.bookflight.entity.FlightDetails;
import io.bookflight.events.FlightBookEvent;
import io.bookflight.exceptionhandler.FlightException;
import io.bookflight.repository.BookFlightRepository;
import io.bookflight.repository.FlightDetailsRepository;
import io.bookflight.service.FlightService;
import io.bookflight.utils.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class FlightServiceImpl implements FlightService {

    private final BookFlightRepository flightRepository;
    private final FlightDetailsRepository detailsRepository;

    private final IdUtils utils;


    private final ApplicationEventPublisher publisher;

    public FlightServiceImpl(BookFlightRepository flightRepository, FlightDetailsRepository detailsRepository, IdUtils utils, ApplicationEventPublisher publisher) {
        this.flightRepository = flightRepository;
        this.detailsRepository = detailsRepository;
        this.utils = utils;
        this.publisher = publisher;
    }


    @Override
    public BookFlight saveFlightBooking(String airLine, String departureCity, String destination, String userId, LocalDate bookingDate) {
        log.info("user id {} ", userId);
        BookFlight newFlight = new BookFlight(airLine, departureCity, destination, userId, bookingDate);
        newFlight.setFlightBookingId(utils.setRandomLongId());
        publisher.publishEvent(new FlightBookEvent("Flight Booked",newFlight));
        flightRepository.save(newFlight);
        return newFlight;
    }

    @Override
    public BookFlight updateBookedFlight(long flightBookingId, BookFlight bookFlight) {
        return flightRepository.findAll()
                .stream()
                .filter(booking -> booking.getFlightBookingId() == flightBookingId)
                .findFirst()
                .map(updatedBooking -> {
                    BookFlight bookingFlight = new BookFlight(bookFlight.getAirLine(),
                            bookFlight.getDepartureCity(), bookFlight.getDestination(), bookFlight.getUserId(), bookFlight.getBookingDate());
                    log.info("{}  ", bookingFlight);
                    bookingFlight.setFlightBookingId(flightBookingId);
                    flightRepository.save(bookingFlight);
                    return bookingFlight;
                }).orElseThrow(() -> new FlightException("", 404));
    }

    @Override
    public List<BookFlight> getAllBookings() {
        return flightRepository.findAll();
    }

    @Override
    public BookFlight getById(long id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightException("", 404));
    }

    @Override
    public List<BookFlight> getBookingDetails(String departCity, String destination, String userId) {

        return flightRepository.
                findByDepartureCityAndDestinationAndUserId(departCity,
                        destination, userId).orElseThrow(() -> new FlightException("", 404));
    }

    @Override
    public FlightDetails saveFlightDetails(String airLine, String departureCity, String destination, LocalDate bookingDate, Long ticketPrice) {
        FlightDetails flightDetails = new FlightDetails(airLine, departureCity, destination, bookingDate, ticketPrice);
        flightDetails.setFlightId(utils.setRandomLongId());
        detailsRepository.save(flightDetails);
        return flightDetails;
    }

    @Override
    public FlightDetails updateFlightDetails(long flightId, FlightDetails flightDetails) {
        List<FlightDetails> details = detailsRepository.findAll();
        log.info("id is {} ", flightId);
        return details.stream()
                .filter(x -> {
                    long id = x.getFlightId();
                    return id == flightId;
                }).findFirst()
                .map(flightDetails1 -> {
                    FlightDetails updatedFlight = new FlightDetails(flightDetails.getAirLine(), flightDetails.getDepartureCity(), flightDetails.getDestination(), flightDetails.getAvailableDate(), flightDetails.getTicketPrice());
                    updatedFlight.setFlightId(flightId);
                    log.info(" {} ", updatedFlight);
                    detailsRepository.save(updatedFlight);
                    return updatedFlight;
                }).orElseThrow(() -> new FlightException("Flight Details not found", 404));
    }


    @Override
    public List<FlightDetails> getAllFlights() {
        return detailsRepository.findAll();
    }


    public List<FlightDetails> getFlightDetails(String departCity, String destination, LocalDate date) {
        return detailsRepository
                .findByDepartureCityAndDestinationAndAvailableDate
                        (departCity, destination, date).orElseThrow(() -> new FlightException("", 404));

    }


}
