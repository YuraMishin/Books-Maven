package com.mishinyura.booksmaven.rest.v1;

import com.mishinyura.booksmaven.models.Book;
import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(BookRestController.BASE_URL)
public class BookRestController {
    public static final String BASE_URL = "/api/v1/books";

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> findAllUsers() {
        return bookService.findAllBooks();
    }
}
