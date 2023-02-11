package xyz.ITMO.Exercise.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class MyException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

}
