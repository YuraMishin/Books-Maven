package com.mishinyura.booksmaven.controllers;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.services.BookService;
import com.mishinyura.booksmaven.utils.exceptions.BookNotFoundExceptionMVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
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
        return bookService.findBookByIdMVC(model, id, "book/show");
    }

    @GetMapping(value = "/new")
    public String showCreateBookPage(@ModelAttribute("book") BookReqDto book) {
        return "book/new";
    }

    @PostMapping(value = "/")
    public String createBook(
            @ModelAttribute("book") @Valid BookReqDto book,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute("message",
                "The book has been saved successfully!");
        return bookService.createBookMVC(book, bindingResult);
    }

    @GetMapping(value = "/{id}/edit")
    public String showEditBookPage(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            return bookService.findBookByIdMVC(model, id, "book/edit");
        } catch (BookNotFoundExceptionMVC exceptionMVC) {
            redirectAttributes.addFlashAttribute(
                    "message",
                    exceptionMVC.getMessage());
            return "redirect:/books";
        }
    }

    @PatchMapping("/{id}")
    public String updateBook(
            @PathVariable("id") Long id,
            @ModelAttribute("book") BookReqDto book,
            RedirectAttributes redirectAttributes
    ) {
        bookService.updateBook(id, book);
        redirectAttributes.addFlashAttribute(
                "message",
                "The book has been updated successfully!");
        return "redirect:/books/";
    }

    @DeleteMapping(value = "/{id}")
    public String delete(
            @PathVariable("id") final Long id,
            RedirectAttributes redirectAttributes
    ) {
        StopWatch countdown = new StopWatch();
        countdown.start();

        String className = getClass().getSimpleName();
        String methodName = Thread.currentThread()
                .getStackTrace()[1].getMethodName();
        log.info("Executed {}.{}()", className, methodName);

        bookService.deleteBookById(id);

        countdown.stop();
        log.info("Execution time of {}.{}():: {}ms", className, methodName, countdown.getTotalTimeMillis());
        redirectAttributes.addFlashAttribute(
                "message",
                "The book has been deleted successfully!");
        return "redirect:/books/";
    }
}
