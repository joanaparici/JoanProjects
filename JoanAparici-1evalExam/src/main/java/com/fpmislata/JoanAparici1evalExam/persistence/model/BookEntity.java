package com.fpmislata.JoanAparici1evalExam.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

        private String isbn;
        private String title;
        private String synopsis;
        private int publisherId;
        private List<Integer> actorIds;

}
