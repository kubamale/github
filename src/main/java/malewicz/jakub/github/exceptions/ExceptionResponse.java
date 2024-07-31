package malewicz.jakub.github.exceptions;

import org.springframework.http.HttpStatus;

public record ExceptionResponse(HttpStatus status, String message) {
}
