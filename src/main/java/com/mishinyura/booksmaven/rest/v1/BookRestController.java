package com.mishinyura.booksmaven.rest.v1;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(BookRestController.BASE_URL)
public class BookRestController {
    public static final String BASE_URL = "/api/v1/books";

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResDto> findAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BookResDto findBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody @Valid BookReqDto bookReqDto, BindingResult bindingResult) {
        bookService.createBook(bookReqDto, bindingResult);
    }
}
