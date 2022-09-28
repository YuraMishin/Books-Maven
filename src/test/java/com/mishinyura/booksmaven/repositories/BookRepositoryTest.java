package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.models.Book;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookRepository.class")
@DataJpaTest
@ActiveProfiles("test-orm")
@RequiredArgsConstructor
class BookRepositoryTest {
    private final BookRepository bookRepository;

    @DisplayName("tests count()")
    @Test
    void shouldGetBooksCount() {
        // given
        var expectedCount = 1L;

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
        var sizeExpected = 2;

        // when
        var books = bookRepository.findAllBooks();

        // then
        assertThat(books)
                .isNotEmpty()
                .hasSize(sizeExpected);
    }

    @DisplayName("tests findById()")
    @Test
    void shouldTestFindById() {
        // given
        var bookExpected = new Book("Title1");
        bookRepository.save(bookExpected);

        // when
        var bookActual = bookRepository.findById(2L).get();

        // then
        assertThat(bookActual)
                .isNotNull()
                .isInstanceOf(Book.class);
        assertThat(bookActual.getTitle()).isEqualTo(bookExpected.getTitle());
    }
}
