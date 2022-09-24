package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    @Query(value = "from Book")
    List<Book> findAllBooks();
}
