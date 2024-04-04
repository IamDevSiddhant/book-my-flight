package io.bookflight.controller;


import io.bookflight.entity.BookFlight;
import io.bookflight.entity.FlightDetails;
import io.bookflight.events.FlightBookEvent;
import io.bookflight.repository.BookFlightRepository;
import io.bookflight.service.FlightService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/interactions")
@Slf4j
public class FlightController {

    private final FlightService flightService;
    private final BookFlightRepository repo;




    public FlightController(FlightService flightService, BookFlightRepository repo) {
        this.flightService = flightService;
        this.repo = repo;

    }


    @PostMapping("/bookFlight")
    public ResponseEntity<?> saveFlight(@RequestBody BookFlight flightUser) {
        log.info("in book flight");
        String userId = flightUser.getUserId();

        //TO-DO
        /*
        * add User Object in BookFlight Class
        * in order to get more data user & booking data needs to be there in final booking        *  will be done at last point after payment done.
        * */


        return new ResponseEntity<>(flightService.saveFlightBooking(flightUser.getAirLine(), flightUser.getDepartureCity()
                , flightUser.getDestination(), userId, flightUser.getBookingDate())
                , HttpStatus.CREATED);
    }

    @PostMapping("/saveFlightDetails")
    public ResponseEntity<?> saveFlightDetails(@RequestBody FlightDetails flightDetails) {
        log.info("in book flight");

        return new ResponseEntity<>(flightService.saveFlightDetails
                (flightDetails.getAirLine(), flightDetails.getDepartureCity(), flightDetails.getDestination(), flightDetails.getAvailableDate(), flightDetails.getTicketPrice())
                , HttpStatus.CREATED);
    }

    @PutMapping("/updateFlightDetails")
    public ResponseEntity<?> updateFlight(@RequestParam(name = "flightId") long flightId, @RequestBody FlightDetails flightDetails) {
        return new ResponseEntity<>(flightService.updateFlightDetails(flightId, flightDetails), HttpStatus.OK);
    }


    @GetMapping("/getDetails")
    public ResponseEntity<?> getDetails(@RequestParam(name = "departureCity", required = true) String departureCity,
                                        @RequestParam(name = "destination", required = true) String destination,
                                        @RequestParam(name = "availableDate", required = true) LocalDate availableDate) {
        return new ResponseEntity<>(flightService.getFlightDetails(departureCity, destination, availableDate), HttpStatus.OK);
    }

    @GetMapping("/getAllFlights")
    public ResponseEntity<?> getAllFlights() {
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    @PutMapping("/updateBookingDetails")
    public ResponseEntity<?> updateBooking(@RequestParam(name = "flightBookingId", required = true) long flightBookingId, @RequestBody BookFlight bookFlight) {
        return new ResponseEntity<>(flightService.updateBookedFlight(flightBookingId, bookFlight), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBooking")
    public ResponseEntity<?> deleteBooking(@RequestParam(name = "flightBookingId", required = true) long flightBookingId) {
        repo.deleteById(flightBookingId);
        return new ResponseEntity<>("Booking Deleted!", HttpStatus.OK);
    }


    @GetMapping("/getAllBooking")
    public ResponseEntity<?> getAllBookings() {
        return new ResponseEntity<>(flightService.getAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/getBookingById/{flightBookingId}")
    public ResponseEntity<?> getById(@PathVariable("flightBookingId") long flightBookingId) {
        return new ResponseEntity<>(flightService.getById(flightBookingId), HttpStatus.OK);
    }

    @GetMapping("/getBooking")
    public ResponseEntity<?> getByParams(@RequestParam(name = "departureCity") String departureCity,
                                         @RequestParam(name = "destination") String destination,
                                         @RequestParam(name = "userId") String userId) {
        return new ResponseEntity<>(flightService.getBookingDetails(departureCity, destination, userId), HttpStatus.OK);
    }
}
