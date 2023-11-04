package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Actor;

import java.util.Optional;

public interface ActorRepository {
    int insert(Actor actor);
    Optional<Actor> find(int id);

    void update(Actor actor);

    void delete(int id);
}