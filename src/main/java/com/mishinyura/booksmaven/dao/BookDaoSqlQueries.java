package com.mishinyura.booksmaven.dao;

public final class BookDaoSqlQueries {
    private BookDaoSqlQueries() {
    }

    public static final String GETBOOKSCOUNT = """
            select count(*)
            from books;
            """;
}
