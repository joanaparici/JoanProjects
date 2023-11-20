package com.fpmislata.movies.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Director {
    String name;
    int id;
    int birthYear;
    Integer deathYear;

    public Director(int id, String name, int birthYear, Integer deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }


    public Director(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }


}
