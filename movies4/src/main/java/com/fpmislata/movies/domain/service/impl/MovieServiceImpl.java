package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repository.ActorRepository;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import com.fpmislata.movies.domain.repository.MovieRepository;
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
    public int getTotalNumberOfRecords() {
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(Movie movie, int directorId, List<Integer> actorIds) {
        Director director = directorRepository.find(directorId)
                .orElseThrow(() -> new ResourceNotFoundException("Director not found with id: " + directorId));
        List<Actor> actors = actorIds.stream()
                .map(actorId -> actorRepository.find(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + actorId))
                )
                .toList();
        movie.setDirector(director);
        movie.setActors(actors);
        return movieRepository.insert(movie);
    }

    @Override
    public void delete(int id) {
        movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: "));
        movieRepository.delete(id);
    }

    @Override
    public void update(Movie movie) {
        movieRepository.find(movie.getId()).orElseThrow(() -> new ResourceNotFoundException("Actor no encontrado con id: " + movie.getId()));
        movieRepository.update(movie);
    }
    @Override
    public Movie find(int id) {
        Movie movie = movieRepository.find(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: "));

        Director director = directorRepository.findDirectorByMovieId(id).orElse(null);
        List<Actor> actor = actorRepository.findByMovieId(id);
        movie.setDirector(director);
        movie.setActors(actor);


        return movie;
    }
    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Movie not found with title: " + title));

    }


}