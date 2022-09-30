package com.mishinyura.booksmaven.utils;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookDtoReqToBook(BookReqDto bookDTO);

    BookResDto bookToBookResDto(Book book);

    List<BookResDto> getListOfBookResDto(List<Book> books);
}
