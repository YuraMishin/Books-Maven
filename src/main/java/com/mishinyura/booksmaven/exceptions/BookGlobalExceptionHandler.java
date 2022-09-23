package com.mishinyura.booksmaven.exceptions;

import com.mishinyura.booksmaven.dto.BookErrorResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class BookGlobalExceptionHandler {
    @ExceptionHandler
    private ResponseEntity<BookErrorResDto> handleBookNotFoundException(BookNotFoundException e) {
        var response = new BookErrorResDto(
                "Book not found !",
                LocalDateTime.now()
        );
        log.error("{} - Exception caught - {}", response.date(), response.message());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResDto> handleBookNotCreatedException(BookNotCreatedException e) {
        var response = new BookErrorResDto(
                e.getMessage(),
                LocalDateTime.now()
        );
        log.error("{} - Exception caught - {}", response.date(), response.message());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
