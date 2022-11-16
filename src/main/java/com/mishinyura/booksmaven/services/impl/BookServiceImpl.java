package com.mishinyura.booksmaven.services.impl;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.entities.Book;
import com.mishinyura.booksmaven.repositories.BookRepository;
import com.mishinyura.booksmaven.services.BookService;
import com.mishinyura.booksmaven.utils.exceptions.BookNotCreatedException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundException;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundExceptionMVC;
import com.mishinyura.booksmaven.utils.validators.BookValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final BookValidator bookValidator;

    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    @Override
    public List<BookResDto> findAllBooks() {
        var books = bookRepository.findAll();
        return modelMapper
                .map(books, new TypeToken<List<BookResDto>>() {
                }.getType());
    }

    @Transactional
    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public BookResDto findBookById(Long id) {
        var bookFound = bookRepository.findById(id);
        var book = bookFound.orElseThrow(BookNotFoundException::new);
        return modelMapper.map(book, BookResDto.class);
    }

    @Override
    public String findBookByIdMVC(Model model, Long id, String page) {
        var bookFound = bookRepository.findById(id);
        if (bookFound.isPresent()) {
            var bookResDto = modelMapper.map(bookFound.get(), BookResDto.class);
            model.addAttribute("book", bookResDto);
            return page;
        }
        throw new BookNotFoundExceptionMVC("Book not found !");
    }

    @Transactional
    @Override
    public void createBook(BookReqDto book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new BookNotCreatedException(errorMsg.toString().trim());
        }
        var bookToSave = modelMapper.map(book, Book.class);
        bookRepository.save(bookToSave);
    }

    @Transactional
    @Override
    public String createBookMVC(BookReqDto book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "book/new";
        }
        bookRepository.save(modelMapper.map(book, Book.class));
        return "redirect:/books/";
    }

    @Transactional
    @Override
    public void updateBook(Long id, BookReqDto book) {
        bookRepository.findById(id)
                .ifPresent(bookToSave -> {
                    modelMapper.map(book, bookToSave);
                    bookRepository.save(bookToSave);
                });
    }

    @Transactional
    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<BookReqDto> findBookByTitle(String title) {
        var bookFound = bookRepository.findByTitle(title);
        if (bookFound.isPresent()) {
            return Optional.of(modelMapper.map(bookFound.get(), BookReqDto.class));
        }
        return Optional.empty();
    }

    @Override
    public boolean isBookUnique(String title) {
        return Objects.isNull(bookRepository.getBookByTitle(title));
    }
}
