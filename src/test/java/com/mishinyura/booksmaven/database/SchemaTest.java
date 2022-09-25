package com.mishinyura.booksmaven.database;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests Database schema")
@JdbcTest
@ActiveProfiles("test-jdbc")
class SchemaTest {
    @Autowired
    private DataSource dataSource;

    @DisplayName("tests database has hibernate tables")
    @Test
    void shouldTestSchema() throws Exception {
        Set<String> tablesExpected = Set.of(
                "BOOKS"
        );

        Set<String> tablesActual = new HashSet<>();
        try (ResultSet rs = Objects.requireNonNull(dataSource.getConnection().getMetaData()
                .getTables(null, null, null, new String[]{"TABLE"}))) {
            while (rs.next()) {
                tablesActual.add(rs.getString("TABLE_NAME"));
            }
        }

        assertThat(tablesActual).containsAll(tablesExpected);
    }
}
