package com.mishinyura.booksmaven.utils.handlers;

import com.mishinyura.booksmaven.dto.ErrorResDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractExceptionHandler {
    private static final Logger log = LogManager.getLogger(AbstractExceptionHandler.class);

    protected ResponseEntity<ErrorResDto> buildErrorResponse(final Throwable throwable,
                                                             final HttpStatus httpStatus) {
        log.error(throwable.getMessage(), throwable);
        return new ResponseEntity<>(buildErrorResponseDTO(throwable.getMessage()), httpStatus);
    }

    protected ResponseEntity<ErrorResDto> buildErrorResponse(final Throwable throwable, final String message,
                                                             final HttpStatus httpStatus) {
        log.error(throwable.getMessage(), throwable);
        return new ResponseEntity<>(buildErrorResponseDTO(message), httpStatus);
    }

    private ErrorResDto buildErrorResponseDTO(final String message) {
        return new ErrorResDto(message);
    }
}
