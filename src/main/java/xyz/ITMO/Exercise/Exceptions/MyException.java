package xyz.ITMO.Exercise.Exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class MyException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

}
