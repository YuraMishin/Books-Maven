package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.base.BaseTest;
import com.mishinyura.booksmaven.entities.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class BookRepositoryTest extends BaseTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

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

    @DisplayName("tests save()")
    @Test
    void shouldTestSave() {
        // given
        var titleExpected = "Book2";
        var book = new Book().setTitle(titleExpected);
        var expectedId = 2L;

        // when
        var bookSaved = bookRepository.save(book);
        var bookFound = em.find(Book.class, expectedId);

        // then
        assertThat(bookSaved)
                .isNotNull()
                .isInstanceOf(Book.class)
                .hasFieldOrPropertyWithValue("title", titleExpected);

        assertThat(bookSaved.getId())
                .isPositive()
                .isEqualTo(expectedId);

        assertThat(bookSaved).isEqualTo(bookFound);
    }

    @DisplayName("tests update()")
    @Test
    void shouldTestUpdate() {
        // given
        var titleExpected = "BookUpdated";
        var expectedId = 1L;
        var book = bookRepository.findById(1L).get();

        // when
        book.setTitle(titleExpected);
        bookRepository.save(book);
        var bookFound = em.find(Book.class, expectedId);

        // then
        assertThat(bookFound)
                .isNotNull()
                .isInstanceOf(Book.class)
                .hasFieldOrPropertyWithValue("title", titleExpected);
    }

    @DisplayName("tests deleteById()")
    @Test
    void shouldTestDeleteById() {
        // given
        var expectedId = 1L;

        // when
        bookRepository.deleteById(expectedId);
        var bookFound = em.find(Book.class, expectedId);

        // then
        assertThat(bookFound).isNull();
    }

    @DisplayName("tests getBookByTitle()")
    @Test
    void shouldTestGetBookByTitle() {
        // given
        var titleExpected = "Title_liquibase";

        // when
        var book = bookRepository.getBookByTitle(titleExpected);

        // then
        assertThat(book).isNotNull();
    }

    @DisplayName("tests getBookByTitle() Negative")
    @Test
    void shouldTestGetBookByTitleNegative() {
        // given
        var titleExpected = "Title_liquibase_Negative";

        // when
        var book = bookRepository.getBookByTitle(titleExpected);

        // then
        assertThat(book).isNull();
    }
}
