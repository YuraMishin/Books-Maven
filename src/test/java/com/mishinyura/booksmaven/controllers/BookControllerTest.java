package com.mishinyura.booksmaven.controllers;

import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Tests BookController.class")
@RequiredArgsConstructor
@WebMvcTest(BookController.class)
@ActiveProfiles("test-h2")
class BookControllerTest {
    private final MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private Model model;

    @DisplayName("tests showHomePage()")
    @Test
    void shouldTestShowHomePage() {
        // given
        var bookController = new BookController(bookService);
        var viewNameExpected = "book/index";
        var books = new ArrayList<>(List.of(
                new BookResDto().setId(1L).setTitle("Title1")
        ));
        when(bookService.findAllBooks()).thenReturn(books);
        var argumentCaptor = ArgumentCaptor.forClass(ArrayList.class);

        // when
        var viewName = bookController.showHomePage(model);

        // then
        assertThat(viewName).isEqualTo(viewNameExpected);
        verify(bookService, times(1)).findAllBooks();
        verify(model, times(1)).addAttribute(eq("books"), argumentCaptor.capture());

        assertThat(argumentCaptor.getValue())
                .isNotEmpty()
                .hasSize(1)
                .map(o -> ((BookResDto) o).getTitle())
                .contains("Title1");
    }

    @DisplayName("tests showHomePage() via mockMVC")
    @Test
    void shouldTestShowHomePageViaMockMVC() throws Exception {
        // given
        var path = "/books";
        var viewName = "book/index";
        var books = new ArrayList<>(List.of(
                new BookResDto().setId(1L).setTitle("Title1")
        ));
        when(bookService.findAllBooks()).thenReturn(books);

        // when-then
        mockMvc
                .perform(get(path))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", hasSize(books.size())))
                .andExpect(view().name(viewName));

        verify(bookService, times(1)).findAllBooks();
    }
}