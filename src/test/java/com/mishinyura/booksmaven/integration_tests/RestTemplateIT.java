package com.mishinyura.booksmaven.integration_tests;

import com.mishinyura.booksmaven.dto.BookResDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests BookRestController.class")
@ActiveProfiles("test-orm")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestTemplateIT {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private RestTemplate restTemplate;

    @DisplayName("tests findBookById()")
    @Test
    void shouldTestFindBookById() {
        // given
        var endpoint = String.format(
                "http://localhost:%s/api/v1/books/1",
                randomServerPort
        );
        var bookExpected = new BookResDto(1L, "TitleBookSeeder");

        // when
        var bookActual = restTemplate.getForObject(endpoint, BookResDto.class);

        // then
        assertThat(bookActual)
                .isNotNull()
                .isInstanceOf(BookResDto.class);
        assertThat(bookActual.getId()).isEqualTo(bookExpected.getId());
        assertThat(bookActual.getTitle()).isEqualTo(bookExpected.getTitle());
    }
}
