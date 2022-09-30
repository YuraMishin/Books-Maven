package com.mishinyura.booksmaven.dao;

import com.mishinyura.booksmaven.models.Book;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookDaoJpaImpl.class")
@Sql({
        "classpath:sql/data.sql"
})
@DataJpaTest
@Import(BookDaoJpaImpl.class)
@ActiveProfiles("test-orm")
@RequiredArgsConstructor
class BookDaoJpaImplTest {
    private final TestEntityManager em;

    private final BookDaoJpaImpl bookDaoJpa;

    @DisplayName("tests getBooksCount()")
    @Test
    void shouldGetBooksCount() {
        // given
        var expectedCount = 1L;

        // when
        var actualCount = bookDaoJpa.getBooksCount();

        // then
        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }

    @DisplayName("tests findAllBooks()")
    @Test
    void shouldFindAllBooks() {
        // when
        var books = bookDaoJpa.findAllBooks();

        // then
        assertThat(books)
                .isNotEmpty()
                .hasSize(1)
                .map(Book::getTitle)
                .contains("Title1_H2");
    }
}
