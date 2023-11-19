package com.fpmislata.movies.controller.model.actor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ActorUpdateWeb {
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
}
