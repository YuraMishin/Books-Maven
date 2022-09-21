package com.mishinyura.booksmaven.utils;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class BookValidator implements Validator {
    private final BookRepository bookRepository;

    @Override
    public boolean supports(final Class<?> clazz) {
        return BookReqDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        BookReqDto book = (BookReqDto) target;
        if (bookRepository.findByTitle(book.getTitle()).isPresent()) {
            errors.rejectValue("title", "", "This title is already taken!");
        }
    }
}
