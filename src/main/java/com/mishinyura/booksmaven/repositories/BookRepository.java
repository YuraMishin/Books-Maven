package com.mishinyura.booksmaven.repositories;

import com.mishinyura.booksmaven.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
