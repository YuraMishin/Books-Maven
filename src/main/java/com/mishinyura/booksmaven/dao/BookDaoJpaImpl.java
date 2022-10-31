package com.mishinyura.booksmaven.dao;

import com.mishinyura.booksmaven.entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookDaoJpaImpl implements BookDao {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Long getBooksCount() {
        return em
                .createQuery("select count(b) from Book b", Long.class)
                .getSingleResult();
    }

    public List<Book> findAllBooks() {
        return em.createQuery("from Book").getResultList();
    }
}
