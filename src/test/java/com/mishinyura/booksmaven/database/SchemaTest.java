package com.mishinyura.booksmaven.database;

import com.mishinyura.booksmaven.base.BaseTest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Tests Database schema")
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RequiredArgsConstructor
class SchemaTest extends BaseTest {
    private final DataSource dataSource;

    @DisplayName("tests database has hibernate tables")
    @Test
    void shouldTestSchema() throws Exception {
        // given
        Set<String> tablesExpected = Set.of(
                "books"
        );

        // when
        Set<String> tablesActual = new HashSet<>();
        try (ResultSet rs = Objects.requireNonNull(dataSource.getConnection().getMetaData()
                .getTables(null, null, null, new String[]{"TABLE"}))) {
            while (rs.next()) {
                tablesActual.add(rs.getString("TABLE_NAME"));
            }
        }

        log.info("\n\nDatabase has tables: {}\n", tablesActual);

        // then
        assertThat(tablesActual).containsAll(tablesExpected);
    }
}
