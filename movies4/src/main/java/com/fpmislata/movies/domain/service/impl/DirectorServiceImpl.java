package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.domain.service.DirectorRepository;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int create(Director director) {
        return directorRepository.insert(director);
    }

    @Override
    public void update(Director director) {
        Director existingDirector = directorRepository.find(director.getId());
        if (existingDirector==null){
            throw new ResourceNotFoundException("Director no encontrado con id: " + director.getId());
        }
        directorRepository.update(director);
    }

    @Override
    public void delete(int id) {
        Director director = directorRepository.find(id);
        if (director==null){
            throw new ResourceNotFoundException("Director no encontrado con id: " + id);
        }
        directorRepository.delete(id);
    }

    @Override
    public Director find(int id) {
        return directorRepository.find(id);
    }
}