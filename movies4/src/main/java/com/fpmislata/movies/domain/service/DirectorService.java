package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Director;

public interface DirectorService {

    public int create(Director director);

    public void update(Director director);

    public void delete(int id);

    Director find(int id);
}

