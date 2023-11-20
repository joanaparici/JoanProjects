package com.fpmislata.movies.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Actor {

    private int id;
    private String name;
    private int birthYear;
    //deathYear es de tipo Integer para poder pasarle nulos
    private Integer deathYear;


    public Actor(int id, String name, int birthYear, Integer deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Actor(String name, int birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }


}
