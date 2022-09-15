package com.mishinyura.booksmaven.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookDaoJpaImpl.class")
@DataJpaTest
@Import(BookDaoJpaImpl.class)
@ActiveProfiles("test-orm")
class BookDaoJpaImplTest {
    @Autowired
    TestEntityManager em;

    @Autowired
    BookDao bookDaoJpa;

    @DisplayName("tests count()")
    @Test
    void shouldGetsBooksCount() {
        var expectedCount = 0L;

        var actualCount = bookDaoJpa.getBooksCount();

        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }
}
