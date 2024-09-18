package br.com.srv.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DefaultErrorException.class)
    public ResponseEntity<ErrorDetails> handleDefaultErrorException(DefaultErrorException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                ex.getStatus().value()
        );
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }
}
