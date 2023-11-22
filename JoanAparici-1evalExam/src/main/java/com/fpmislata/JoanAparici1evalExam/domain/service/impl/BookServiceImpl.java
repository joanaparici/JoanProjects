package com.fpmislata.JoanAparici1evalExam.domain.service.impl;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Author;
import com.fpmislata.JoanAparici1evalExam.domain.entity.Book;
import com.fpmislata.JoanAparici1evalExam.domain.entity.Publisher;
import com.fpmislata.JoanAparici1evalExam.domain.repository.AuthorRepository;
import com.fpmislata.JoanAparici1evalExam.domain.repository.BookRepository;
import com.fpmislata.JoanAparici1evalExam.domain.repository.PublisherRepository;
import com.fpmislata.JoanAparici1evalExam.domain.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book findByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("Book not found with isbn: " + isbn));
        Publisher publisher = publisherRepository.findPublisherByIsbn(isbn).orElse(null);
        List<Author> author = authorRepository.findByIsbn(isbn);
        book.setPublisher_id(publisher.getId());
        book.setAuthors(author);

        return book;
    }



}
