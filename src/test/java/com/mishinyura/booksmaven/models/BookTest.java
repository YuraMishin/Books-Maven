package com.mishinyura.booksmaven.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests Book.class")
@ActiveProfiles("test-h2")
class BookTest {
    @DisplayName("tests getTitle()")
    @Test
    void shouldTestGetTitle() {
        // given
        var titleExpected = "Title";
        var book = new Book(titleExpected);

        // when
        var titleActual = book.getTitle();

        // then
        assertThat(titleActual).isEqualTo(titleExpected);
    }
}
