package com.fpmislata.movies.domain.service;

import java.util.List;
import java.util.Optional;

import com.fpmislata.movies.domain.entity.Movie;
import org.springframework.stereotype.Service;

public interface MovieService {
    List<Movie> getAll(Integer page, Integer pageSize);
    List<Movie> getAll();

    Movie find(int id);

    int getTotalNumberOfRecords();
}