package io.bookflight.events;

import io.bookflight.serviceimpl.SendEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Component
@Slf4j
public class FlightBookEventListener implements ApplicationListener<FlightBookEvent> {

    private final SendEmailService emailService;

    private static final String toEmail="siddhantpawar572@gmail.com";
    private static final String subject="Flight Booking Confirmation";

    public FlightBookEventListener(SendEmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(FlightBookEvent event) {
        sendBookingConfirmEmail(event);
    }

    @Async("asyncTaskExecutor")
    public void sendBookingConfirmEmail(FlightBookEvent event){
        try {
            Thread.sleep(750L);
            log.info("Thread Name is {} ",Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.info(e.getLocalizedMessage());
        }



        emailService.sendEmail(toEmail,subject,"Your Flight is Booked, please find the booking details below" +
                "\nBooking Id:- "+event.getBookFlight().getFlightBookingId()+
                "\n Date:- "+event.getBookFlight().getBookingTime()+
                "\n Departure City:- "+event.getBookFlight().getDepartureCity()+
                "\n Destination City:- "+event.getBookFlight().getDestination());
    }



}
