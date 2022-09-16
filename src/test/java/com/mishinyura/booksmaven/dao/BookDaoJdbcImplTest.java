package com.mishinyura.booksmaven.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookDaoJdbcImpl.class")
@JdbcTest
@Import(BookDaoJdbcImpl.class)
@ActiveProfiles("test-jdbc")
class BookDaoJdbcImplTest {
    @Autowired
    BookDao bookDaoJdbc;

    @DisplayName("tests count()")
    @Test
    void shouldGetBooksCount() {
        var expectedCount = 0L;

        var actualCount = bookDaoJdbc.getBooksCount();

        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }
}
