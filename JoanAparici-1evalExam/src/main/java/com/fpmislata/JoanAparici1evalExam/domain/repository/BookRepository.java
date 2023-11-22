package com.fpmislata.JoanAparici1evalExam.domain.repository;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getAll();

    Optional<Book> findByIsbn(String isbn);
}
