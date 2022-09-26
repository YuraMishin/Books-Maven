package com.mishinyura.booksmaven.dao;

import com.mishinyura.booksmaven.models.Book;

import java.util.List;

public interface BookDao {
    Long getBooksCount();

    List<Book> findAllBooks();
}
