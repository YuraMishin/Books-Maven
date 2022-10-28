package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.models.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookRepository.class")
@DataJpaTest
@ActiveProfiles("test-orm")
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("tests count()")
    @Test
    void shouldGetBooksCount() {
        // given
        var expectedCount = 0L;

        // when
        var actualCount = bookRepository.count();

        // then
        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }

    @DisplayName("tests findAllBooks()")
    @Test
    void shouldFindAllBooks() {
        // given
        bookRepository.save(new Book("Title1"));
        var sizeExpected = 1;

        // when
        var books = bookRepository.findAllBooks();

        // then
        assertThat(books)
                .isNotEmpty()
                .hasSize(sizeExpected);
    }

    @DisplayName("tests findById()")
    @DirtiesContext
    @Test
    void shouldTestFindById() {
        // given
        var bookExpected = new Book("Title1");
        var bookSaved = bookRepository.save(bookExpected);

        // when
        var bookActual = bookRepository.findById(bookSaved.getId()).get();

        // then
        assertThat(bookActual)
                .isNotNull()
                .isInstanceOf(Book.class)
                .hasFieldOrPropertyWithValue("title", bookExpected.getTitle());
    }
}
