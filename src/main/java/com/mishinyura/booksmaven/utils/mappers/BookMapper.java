package com.mishinyura.booksmaven.utils.mappers;

import com.mishinyura.booksmaven.dto.BookReqDto;
import com.mishinyura.booksmaven.dto.BookResDto;
import com.mishinyura.booksmaven.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = BookMapper.class)
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book mapToBook(BookReqDto bookDTO);

    BookResDto mapToBookResDto(Book book);

    List<BookResDto> getListOfBookResDto(List<Book> books);

    void updateBookFromDto(BookReqDto bookDTO, @MappingTarget Book book);
}
