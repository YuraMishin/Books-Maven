package com.mishinyura.booksmaven.rest.impl;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.rest.BookRestController;
import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookRestControllerImpl implements BookRestController {
    private final BookService bookService;

    @Override
    public List<BookResDto> getAllBooks() {
        return bookService.findAllBooks();
    }


    @Override
    public BookResDto getBookById(@PathVariable Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody @Valid BookReqDto bookReqDto, BindingResult bindingResult) {
        bookService.createBook(bookReqDto, bindingResult);
    }
}
