package com.fpmislata.JoanAparici1evalExam.domain.service;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book findByIsbn(String isbn);
}
