package com.mishinyura.booksmaven.seeders;

import com.mishinyura.booksmaven.models.Book;
import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class BookSeeder implements CommandLineRunner {
    private final BookService bookService;

    @Override
    public void run(String... args) {
        log.debug("Seed books table");
        bookService.createBook(new Book("title1"));
        log.debug("Books table has {} record(s)", bookService.getBooksCount());
    }
}
