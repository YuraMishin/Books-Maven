package com.mishinyura.booksmaven.dao;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoJdbcImpl implements BookDao {
    private final JdbcOperations jdbc;

    public BookDaoJdbcImpl(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Long getBooksCount() {
        return jdbc.queryForObject(BookDaoSqlQueries.GETBOOKSCOUNT, Long.class);
    }
}
