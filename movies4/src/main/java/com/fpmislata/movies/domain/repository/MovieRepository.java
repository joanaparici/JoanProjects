package com.fpmislata.movies.domain.repository;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.domain.entity.Movie;

public interface MovieRepository {

    List<Movie> getAll(Integer page, Integer pageSize);
    Optional<Movie> find(int id);

    int getTotalNumberOfRecords();

    int insert(Movie movie);

    void delete(int id);

    void update(Movie movie);

    Optional<Movie> findByTitle(String title);
}