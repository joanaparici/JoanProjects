package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Director;

import java.util.Optional;

public interface DirectorRepository {
    int insert(Director director);

    void update(Director director);

    Optional <Director> find(int id);

    void delete(int id);

    Optional<Director> findDirectorByMovieId(int id);
}