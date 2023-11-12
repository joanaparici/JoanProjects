package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.ActorRepository;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.domain.service.DirectorRepository;
import com.fpmislata.movies.domain.service.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        return movieRepository.getAll(page, pageSize);
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.getAll(null, null);
    }

//return movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id);
        Director director = directorRepository.findDirectorByMovieId(movie.getId());
        movie.setDirector(director);
        return movie;
    }

    @Override
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }
}