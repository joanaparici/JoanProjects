package com.fpmislata.movies.controller;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Director create(@RequestBody Director director){
        int id = directorService.create(director);
        director.setId(id);
        return director;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody Director director) {
        director.setId(id);
        directorService.update(director);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }
}