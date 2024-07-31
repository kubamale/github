package malewicz.jakub.github.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionResponse> handleHttpClientErrorException(final HttpClientErrorException exception) {
        if (exception.getStatusCode() == HttpStatusCode.valueOf(404)){
            return new ResponseEntity<>(new ExceptionResponse(NOT_FOUND, "User with that username does not exist!"), NOT_FOUND);
        }

        HttpStatus status = HttpStatus.valueOf(exception.getStatusCode().value());
        return new ResponseEntity<>(new ExceptionResponse(status, exception.getMessage()), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(final Exception exception) {
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR, exception.getMessage()), INTERNAL_SERVER_ERROR);
    }

}
