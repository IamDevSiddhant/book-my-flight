package io.bookflight.events;


import io.bookflight.entity.BookFlight;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter

public class FlightBookEvent extends ApplicationEvent {


    private String publishedMessage;

    private BookFlight bookFlight;

    public FlightBookEvent(String publishedMessage,BookFlight bookFlight) {
        super(publishedMessage);
        this.publishedMessage = publishedMessage;
        this.bookFlight=bookFlight;
    }
}
