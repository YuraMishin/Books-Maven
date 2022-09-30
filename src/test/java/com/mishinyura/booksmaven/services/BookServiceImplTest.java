package com.mishinyura.booksmaven.services;

import com.mishinyura.booksmaven.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Tests BookServiceImpl.class")
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test-orm")
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @DisplayName("tests getBooksCount()")
    @Test
    void shouldTestGetBooksCount() {
        // given
        var booksCountExpected = 0L;
        when(bookRepository.count()).thenReturn(0L);

        // when
        var booksCountActual = bookService.getBooksCount();

        // then
        assertThat(booksCountActual).isEqualTo(booksCountExpected);

        verify(bookRepository, times(1)).count();
    }
}
