package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.base.BaseTest;
import com.mishinyura.booksmaven.entities.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BookRepositoryTest extends BaseTest {
    @Autowired
    private BookRepository bookRepository;

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
        var sizeExpected = 1;

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
        var titleExpected = "Title_liquibase";

        // when
        var bookActual = bookRepository.findById(1L).get();

        // then
        assertThat(bookActual)
                .isNotNull()
                .isInstanceOf(Book.class)
                .hasFieldOrPropertyWithValue("title", titleExpected);
    }
}
