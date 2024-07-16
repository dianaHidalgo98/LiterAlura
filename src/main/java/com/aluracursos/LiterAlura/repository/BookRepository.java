package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByGutenID(Integer gutenID);
    @Query("SELECT b FROM Book b WHERE b.languages LIKE %?1%")
    List<Book> findBooksByLanguage(String language);
}
