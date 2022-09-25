package com.mishinyura.booksmaven.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookDaoJdbcImpl.class")
@Sql({
        "classpath:sql/data.sql"
})
@JdbcTest
@Import(BookDaoJdbcTemplateImpl.class)
@ActiveProfiles("test-jdbc")
class BookDaoJdbcTemplateImplTest {
    @Autowired
    BookDao bookDaoJdbc;

    @DisplayName("tests count()")
    @Test
    void shouldGetBooksCount() {
        var expectedCount = 1L;

        var actualCount = bookDaoJdbc.getBooksCount();

        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }
}
