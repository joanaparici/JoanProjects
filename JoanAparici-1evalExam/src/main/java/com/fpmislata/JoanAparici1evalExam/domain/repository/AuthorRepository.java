package com.fpmislata.JoanAparici1evalExam.domain.repository;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository {

    Optional<Author> find(int id);
    List<Author> findByIsbn(String isbn);
}
