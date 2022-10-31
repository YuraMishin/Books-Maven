package com.mishinyura.booksmaven.utils.exceptions;

public abstract class AbstractResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final String message;

    protected AbstractResourceNotFoundException(final String resourceId) {
        this.message = buildMessage(resourceId);
    }

    protected AbstractResourceNotFoundException() {
        this.message = buildMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    private String buildMessage(final String resourceId) {
        String messageBuilder = getResourceAlias() + " with id " +
                resourceId +
                " is not found.";
        return messageBuilder;
    }

    private String buildMessage() {
        String messageBuilder = "No " + getResourceAlias() +
                "s are found.";
        return messageBuilder;
    }

    protected abstract String getResourceAlias();
}
