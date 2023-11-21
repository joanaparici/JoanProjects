package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.persistence.model.ActorEntity;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    int insert(Actor actor);
    Optional<Actor> find(int id);

    void update(Actor actor);

    void delete(int id);

    List<Actor> findByMovieId(int movieId);

    List<Actor> findByMovieTitle(String title);
}