package com.mishinyura.booksmaven.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class BookMvcExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundExceptionMVC.class)
    public String handleBookNotFoundException(Exception e, Model model) {
        model.addAttribute("exception", e);
        log.error("{} - Exception caught - {}", LocalDateTime.now(), "BookNotFoundExceptionMVC !");
        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(Exception e, Model model) {
        model.addAttribute("exception", e);
        log.error("{} - Exception caught - {}", LocalDateTime.now(), "NumberFormatException !");
        return "error";
    }
}
