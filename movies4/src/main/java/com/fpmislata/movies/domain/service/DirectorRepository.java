package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Director;

import java.util.Optional;

public interface DirectorRepository {
    int insert(Director director);

    void update(Director director);

    Director find(int id);

    void delete(int id);
}