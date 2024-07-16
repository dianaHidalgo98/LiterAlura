package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // En AuthorRepository
    List<Author> findByBirthDateLessThanEqualAndDeathDateGreaterThanEqualOrDeathDateIsNull(Integer birthDateEnd, Integer deathDateStart);

}
