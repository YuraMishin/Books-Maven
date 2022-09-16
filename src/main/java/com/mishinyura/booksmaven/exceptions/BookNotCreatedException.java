package com.mishinyura.booksmaven.exceptions;

public class BookNotCreatedException extends RuntimeException {
    public BookNotCreatedException(final String message) {
        super(message);
    }
}
