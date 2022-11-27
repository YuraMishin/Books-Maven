package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    @Query("select b from Book b where b.title = :title")
    Book getBookByTitle(@Param("title") String title);

    @Query(value = "from Book")
    List<Book> findAllBooks();

    @Query(value = "update Book b set b.enabled = :enabled where b.id = :id")
    @Modifying
    void updateBookEnabledStatus(@Param("id") Long id, @Param("enabled") boolean enabled);
}
