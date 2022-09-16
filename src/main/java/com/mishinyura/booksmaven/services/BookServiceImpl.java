package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.models.Book;
import com.mishinyura.booksmaven.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Long getBooksCount() {
        return bookRepository.count();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }
}
