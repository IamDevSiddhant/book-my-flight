package io.bookflight.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
@Table(name = "Book_Flight")
public class BookFlight {
    @Id
    private long flightBookingId;
    private String airLine;
    @NotNull(message = "departure city cant be null")
    private String departureCity;
    @NotNull(message = "destination city cant be null")
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @NotNull(message = "please provide a date, date cant be null or earlier")
    private LocalDate bookingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss.SSS' '", timezone = "GMT")
    private LocalDateTime bookingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss.SSS' '", timezone = "GMT")
    private LocalDateTime flightArrivalTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' 'HH:mm:ss.SSS' '", timezone = "GMT")
    private LocalDateTime flightDepartureTime;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FLIGHT"))
//    private UserInfo userInfo;

    private String userId;

    public BookFlight(String airLine, String departureCity, String destination, String userId, LocalDate bookingDate) {
        this.airLine = airLine;
        this.departureCity = departureCity;
        this.destination = destination;
        this.bookingTime = timeZone();
        this.bookingDate = bookingDate;
        this.flightArrivalTime = arrivalTime(this.bookingDate);
        this.flightDepartureTime = departTime(this.bookingDate);
        this.userId=userId;
    }



    public LocalDateTime timeZone(){
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    public LocalDateTime arrivalTime(LocalDate bookingDate){
        return LocalDateTime.of(bookingDate,LocalTime.now(ZoneOffset.UTC)).plusHours(2);
    }
    public LocalDateTime departTime(LocalDate bookingDate){
        return LocalDateTime.of(bookingDate,LocalTime.now(ZoneOffset.UTC)).plusHours(5);
    }
}
