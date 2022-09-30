package com.mishinyura.booksmaven.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.services.BookService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@DisplayName("Tests BookRestController.class")
@RequiredArgsConstructor
@WebMvcTest(BookRestController.class)
@ActiveProfiles("test-orm")
class BookRestControllerTest {
    private final MockMvc mockMvc;

    private final WebTestClient webTestClient;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @DisplayName("tests findBookById()_V1")
    @Test
    void shouldTestFindBookById_V1() throws Exception {
        // given
        var endpoint = "/api/v1/books/1";
        var bookExpected = new BookResDto(1L, "Title1");
        when(bookService.findBookById(anyLong())).thenReturn(bookExpected);

        // when
        var mvcResult = mockMvc
                .perform(get(endpoint)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        var content = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        var bookActual = objectMapper.readValue(content, BookResDto.class);

        // then
        assertThat(bookActual)
                .isNotNull()
                .isInstanceOf(BookResDto.class);
        assertThat(bookActual.getId()).isEqualTo(bookExpected.getId());
        assertThat(bookActual.getTitle()).isEqualTo(bookExpected.getTitle());

        verify(bookService, times(1)).findBookById(anyLong());
    }

    @DisplayName("tests findBookById()_V2")
    @Test
    void shouldTestFindBookById_V2() throws Exception {
        // given
        var endpoint = "/api/v1/books/1";
        var bookExpected = new BookResDto(1L, "Title1");
        when(bookService.findBookById(anyLong())).thenReturn(bookExpected);

        // when
        var bookActual = webTestClient
                .get()
                .uri(endpoint)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is3xxRedirection()
                .expectBody(BookResDto.class)
                .returnResult()
                .getResponseBody();

        // then
        assertThat(bookActual.getId()).isEqualTo(bookExpected.getId());
        assertThat(bookActual.getTitle()).isEqualTo(bookExpected.getTitle());

        verify(bookService, times(1)).findBookById(anyLong());
    }

    @DisplayName("tests findBookById()_V3")
    @Test
    void shouldTestFindBookById_V3() throws Exception {
        // given
        var endpoint = "/api/v1/books/1";
        var bookExpected = new BookResDto(1L, "Title1");
        when(bookService.findBookById(anyLong())).thenReturn(bookExpected);

        // when
        var bookActual = RestAssuredMockMvc
                .given()
                .when()
                .get(endpoint)
                .then()
                .status(HttpStatus.FOUND)
                .extract()
                .body()
                .as(BookResDto.class);

        // then
        assertThat(bookActual.getId()).isEqualTo(bookExpected.getId());
        assertThat(bookActual.getTitle()).isEqualTo(bookExpected.getTitle());

        verify(bookService, times(1)).findBookById(anyLong());
    }
}
