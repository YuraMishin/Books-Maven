package com.mishinyura.booksmaven.controllers;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @GetMapping(value = {"", "/"})
    public String showHomePage(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "book/index";
    }

    @GetMapping(value = "/{id}")
    public String showSpecificBookPage(@PathVariable Long id, Model model) {
        return bookService.findBookByIdMVC(model, id);
    }

    @GetMapping(value = "/new")
    public String showCreateBookPage(@ModelAttribute("book") BookReqDto book) {
        return "book/new";
    }

    @PostMapping(value = "/")
    public String createBook(@ModelAttribute("book") BookReqDto book) {
        bookService.createBookMVC(book);
        return "redirect:/books/";
    }
}
