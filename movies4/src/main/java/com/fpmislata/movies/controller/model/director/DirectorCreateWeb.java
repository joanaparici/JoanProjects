package com.fpmislata.movies.controller.model.director;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectorCreateWeb {

    private String name;
    private int birthYear;
    private Integer deathYear;
}