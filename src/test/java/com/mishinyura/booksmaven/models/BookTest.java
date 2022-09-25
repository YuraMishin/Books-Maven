package com.mishinyura.booksmaven.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests Book.class")
class BookTest {

    @DisplayName("tests getTitle()")
    @Test
    void shouldTestGetTitle() {
        var titleExpected = "Title";
        var book = new Book(titleExpected);

        var titleActual = book.getTitle();

        assertThat(titleActual).isEqualTo(titleExpected);
    }
}
