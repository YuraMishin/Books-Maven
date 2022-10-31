package com.mishinyura.booksmaven.utils.exceptions;

public class BookNotFoundException extends AbstractResourceNotFoundException {
    @Override
    protected String getResourceAlias() {
        return "Book";
    }
}
