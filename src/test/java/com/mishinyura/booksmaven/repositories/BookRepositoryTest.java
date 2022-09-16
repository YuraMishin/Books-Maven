package com.mishinyura.booksmaven.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookRepository.class")
@DataJpaTest
@ActiveProfiles("test-orm")
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @DisplayName("tests count()")
    @Test
    void shouldGetBooksCount() {
        var expectedCount = 0L;

        var actualCount = bookRepository.count();

        assertThat(actualCount)
                .as("Error in count()")
                .isEqualTo(expectedCount);
    }
}
