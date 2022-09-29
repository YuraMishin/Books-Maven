package com.mishinyura.booksmaven.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundExceptionMVC extends RuntimeException {
    public BookNotFoundExceptionMVC(String message) {
        super(message);
    }
}
