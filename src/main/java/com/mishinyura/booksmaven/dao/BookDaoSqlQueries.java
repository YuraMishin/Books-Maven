package com.mishinyura.booksmaven.dao;

public final class BookDaoSqlQueries {
    private BookDaoSqlQueries() {
    }

    public static final String GET_BOOKS_COUNT = """
            select count(*)
            from books;
            """;
}
