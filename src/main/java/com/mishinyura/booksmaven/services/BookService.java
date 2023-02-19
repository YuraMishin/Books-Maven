package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Long getBooksCount();

    List<BookResDto> findAllBooks();

    void createBook(Book book);

    BookResDto findBookById(Long id);

    String findBookByIdMVC(Model model, Long id, String page);

    void createBook(BookReqDto book, BindingResult bindingResult);

    String createBookMVC(BookReqDto book, BindingResult bindingResult, MultipartFile multipartFile);

    void updateBook(Long id, BookReqDto book, MultipartFile multipartFile);

    void deleteBookById(Long id);

    Optional<BookReqDto> findBookByTitle(String title);

    boolean isBookUnique(String title);

    void updateBookEnabledStatus(Long id, boolean enabled);

    Page<Book> findAllBooksByPage(int pageNum);

    Page<Book> findAllBooksByPage(int pageNum, String sortField, String sortDir);
}
