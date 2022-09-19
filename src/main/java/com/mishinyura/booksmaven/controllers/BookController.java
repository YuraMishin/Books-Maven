package com.mishinyura.booksmaven.controllers;

import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = {"", "/"})
    public String showHomePage(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books/index";
    }
}
