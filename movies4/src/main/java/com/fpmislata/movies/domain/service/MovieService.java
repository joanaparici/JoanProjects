package com.fpmislata.movies.domain.service;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.http_response.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface MovieService {
    List<Movie> getAll(Integer page, Integer pageSize);
    List<Movie> getAll();

    Movie find(int id);

    int getTotalNumberOfRecords();

    int create(Movie movie, int directorId, List<Integer> actorIds);

    void delete(int id);

    void update(Movie movie);

    Movie findByTitle(String title);
}