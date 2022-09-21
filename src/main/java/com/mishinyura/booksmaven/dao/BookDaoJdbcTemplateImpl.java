package com.mishinyura.booksmaven.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {
    private final JdbcOperations jdbc;

    @Override
    public Long getBooksCount() {
        return jdbc.queryForObject(BookDaoSqlQueries.GET_BOOKS_COUNT, Long.class);
    }
}
