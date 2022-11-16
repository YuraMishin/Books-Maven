package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.entities.Book;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Long getBooksCount();

    List<BookResDto> findAllBooks();

    void createBook(Book book);

    BookResDto findBookById(Long id);

    String findBookByIdMVC(Model model, Long id, String page);

    void createBook(BookReqDto book, BindingResult bindingResult);

    String createBookMVC(BookReqDto book, BindingResult bindingResult);

    void updateBook(Long id, BookReqDto book);

    void deleteBookById(Long id);

    Optional<BookReqDto> findBookByTitle(String title);

    boolean isBookUnique(String title);
}
