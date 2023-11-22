package com.fpmislata.JoanAparici1evalExam.controller.model.book;

import com.fpmislata.JoanAparici1evalExam.controller.model.author.AuthorListWeb;
import com.fpmislata.JoanAparici1evalExam.controller.model.publisher.PublisherListWeb;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookDetailWeb {

    private String isbn;
    private String title;
    private String synopsis;
    private PublisherListWeb publisher;
    private List<AuthorListWeb> authors;
}
