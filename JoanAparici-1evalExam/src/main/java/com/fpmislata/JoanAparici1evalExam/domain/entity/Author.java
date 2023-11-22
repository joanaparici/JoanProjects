package com.fpmislata.JoanAparici1evalExam.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Author {
    private int id;
    private String name;
    private String nationality;
    private int birth_year;
    private Integer death_year;
}
