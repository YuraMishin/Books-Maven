package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.models.Book;

import java.util.List;

public interface BookService {
    Long getBooksCount();

    List<Book> findAllBooks();

    void createBook(Book book);
}
