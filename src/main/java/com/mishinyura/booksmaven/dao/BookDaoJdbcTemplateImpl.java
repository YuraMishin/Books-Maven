package com.mishinyura.booksmaven.dao;

import com.mishinyura.booksmaven.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {
    private final JdbcOperations jdbc;

    @Override
    public Long getBooksCount() {
        return jdbc.queryForObject(BookDaoSqlQueries.GET_BOOKS_COUNT, Long.class);
    }

    @Override
    public List<Book> findAllBooks() {
        throw new UnsupportedOperationException();
    }
}
