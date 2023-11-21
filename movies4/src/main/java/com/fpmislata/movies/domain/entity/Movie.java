package com.fpmislata.movies.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class Movie {

    private int id;
    private String title;
    private int year;
    private int runtime;
    private Director director;
    private List<Actor> actors;

public Movie(int id, String title, int year, int runtime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public Movie(String title, int year, int runtime) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

}