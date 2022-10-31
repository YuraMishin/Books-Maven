package com.mishinyura.booksmaven.utils.handlers;

import com.mishinyura.booksmaven.dto.BookErrorResDto;
import com.mishinyura.booksmaven.dto.ErrorResDto;
import com.mishinyura.booksmaven.utils.exceptions.AbstractResourceNotFoundException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotCreatedException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundExceptionMVC;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends AbstractExceptionHandler {

    @ExceptionHandler(AbstractResourceNotFoundException.class)
    public ResponseEntity<ErrorResDto> resourceNotFoundExceptionHandler(final AbstractResourceNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResDto> bookNotFoundExceptionHandler(final BookNotFoundException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundExceptionMVC.class)
    public String handleBookNotFoundException(Exception e, Model model) {
        model.addAttribute("exception", e);
        log.error("{} - Exception caught - {}", LocalDateTime.now(), "BookNotFoundExceptionMVC !");
        return "error";
    }

    @ExceptionHandler(BookNotCreatedException.class)
    private ResponseEntity<BookErrorResDto> handleBookNotCreatedException(Exception e) {
        var response = new BookErrorResDto(
                e.getMessage(),
                LocalDateTime.now()
        );
        log.error("{} - Exception caught - {}", response.date(), response.message());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
