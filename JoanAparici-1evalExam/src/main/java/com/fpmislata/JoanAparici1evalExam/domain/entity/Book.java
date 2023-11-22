package com.fpmislata.JoanAparici1evalExam.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    private int id;
    private String isbn;
    private String title;
    private String synopsis;
    private int publisher_id;
    private List<Author> authors;
}
