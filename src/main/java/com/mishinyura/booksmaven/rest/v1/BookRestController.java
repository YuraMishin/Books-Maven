package com.mishinyura.booksmaven.rest.v1;

import com.mishinyura.booksmaven.dto.BookErrorResDto;
import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.exceptions.BookNotFoundException;
import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(BookRestController.BASE_URL)
public class BookRestController {
    public static final String BASE_URL = "/api/v1/books";

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResDto> findAllUsers() {
        return bookService.findAllBooks();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BookResDto findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResDto> handleBookNotFoundException(BookNotFoundException e) {
        var response = new BookErrorResDto(
                "Book not found !",
                LocalDateTime.now()
        );
        log.error("{} - Exception caught - {}", response.date(), response.message());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookResDto createUser(@RequestBody BookReqDto bookReqDto) {
        return bookService.createBook(bookReqDto);
    }
}
