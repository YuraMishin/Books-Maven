package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.models.Book;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface BookService {
    Long getBooksCount();

    List<BookResDto> findAllBooks();

    void createBook(Book book);

    BookResDto findBookById(Long id);

    String findBookByIdMVC(Model model, Long id);

    BookResDto createBook(BookReqDto book, BindingResult bindingResult);
}
