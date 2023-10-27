package com.fpmislata.movies.persistence;

import com.fpmislata.movies.domain.entity.Actor;

import java.util.Optional;

public interface ActorRepository {
    int insert(Actor actor);
    Optional<Actor> find(int id);

    void update(Actor actor);

    void delete(int id);
}