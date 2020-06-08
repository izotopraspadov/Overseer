package edu.born.overseer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "USER_DOES_NOT_HAVE_RIGHTS")
public class NoEditingRightsException extends RuntimeException {
    public NoEditingRightsException(String message) {
        super(message);
    }
}
