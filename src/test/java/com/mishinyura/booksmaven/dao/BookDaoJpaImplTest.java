package com.mishinyura.booksmaven.dao;

import com.mishinyura.booksmaven.models.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
class BookDaoJpaImplTest {
    @Autowired
    TestEntityManager em;

    @Autowired
    BookDaoJpaImpl bookDaoJpa;

    @DisplayName("tests count()")
    @Test
    void shouldGetBooksCount() {
        var expectedCount = 1L;

        var actualCount = bookDaoJpa.getBooksCount();

        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }

    @DisplayName("tests findAllBooks()")
    @Test
    void shouldFindAllBooks() {
        var books = bookDaoJpa.findAllBooks();

        assertThat(books)
                .isNotEmpty()
                .hasSize(1)
                .map(Book::getTitle)
                .contains("Title1_H2");
    }
}
