package io.bookflight.exceptionhandler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExceptions.class)
    public ResponseEntity<?> handleUserExceptions(UserExceptions exceptions) {
        return new ResponseEntity<>(exceptions.getErrorMessage(),
                HttpStatusCode.valueOf(exceptions.getErrorStatusCode()));
    }

    @ExceptionHandler(FlightException.class)
    public ResponseEntity<?> handleFlightExceptions(FlightException exceptions) {
        return new ResponseEntity<>(exceptions.getErrorMessage(),
                HttpStatusCode.valueOf(exceptions.getErrorStatusCode()));
    }
}
