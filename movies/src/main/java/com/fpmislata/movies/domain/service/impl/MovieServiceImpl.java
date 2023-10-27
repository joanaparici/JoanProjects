package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll(null, null);
    }


    @Override
    public Movie find(int id) {
        return movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}