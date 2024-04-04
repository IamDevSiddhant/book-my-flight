package io.bookflight.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Flight_Data")
public class FlightDetails {
    @Id
    private long flightId;
    private String airLine;
    @NotNull(message = "departure city cant be null")
    private String departureCity;
    @NotNull(message = "destination city cant be null")
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @NotNull(message = "please provide a date, date cant be null or earlier")
    private LocalDate availableDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss.SSS' '", timezone = "GMT")
    private LocalDateTime expectedArrivalTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss.SSS' '", timezone = "GMT")
    private LocalDateTime expectedDepartureTime;
    private long ticketPrice;


    public FlightDetails(String airLine,String departureCity,String destination,LocalDate availableDate,long ticketPrice){
        this.airLine=airLine;
        this.departureCity=departureCity;
        this.destination=destination;
        this.availableDate=availableDate;
        this.ticketPrice=ticketPrice;
        this.expectedArrivalTime=arrivalTime(this.availableDate);
        this.expectedDepartureTime=departTime(this.availableDate);
    }


    public LocalDateTime arrivalTime(LocalDate bookingDate){
        return LocalDateTime.of(bookingDate, LocalTime.now(ZoneOffset.UTC)).plusHours(2);
    }
    public LocalDateTime departTime(LocalDate bookingDate){
        return LocalDateTime.of(bookingDate,LocalTime.now(ZoneOffset.UTC)).plusHours(5);
    }



}
