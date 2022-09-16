package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.models.Book;

import java.util.List;

public interface BookService {
    Long getBooksCount();

    List<BookResDto> findAllBooks();

    void createBook(Book book);

    BookResDto findBookById(Long id);
}
