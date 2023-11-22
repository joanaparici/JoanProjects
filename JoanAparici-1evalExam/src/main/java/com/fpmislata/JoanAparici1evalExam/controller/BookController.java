package com.fpmislata.JoanAparici1evalExam.controller;

import com.fpmislata.JoanAparici1evalExam.controller.model.book.BookListWeb;
import com.fpmislata.JoanAparici1evalExam.domain.service.BookService;
import com.fpmislata.JoanAparici1evalExam.http_response.Response;
import com.fpmislata.JoanAparici1evalExam.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(BookController.BOOKS)
@RestController
public class BookController {

    public static final String BOOKS = "/books";

    @Autowired
    private BookService bookService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll() {
        List<BookListWeb> booksWeb = bookService.getAll().stream()
                .map(book -> BookMapper.mapper.toBookListWeb(book))
                .toList();
        return Response.builder()
                        .data(booksWeb)
                        .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{isbn}")
    public Response findByIsbn(@PathVariable("isbn") String isbn) {
        return Response.builder()
                .data(BookMapper.mapper.toBookDetailWeb(bookService.findByIsbn(isbn)))
                .build();
    }


}
