package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.models.Book;
import com.mishinyura.booksmaven.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

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
}
