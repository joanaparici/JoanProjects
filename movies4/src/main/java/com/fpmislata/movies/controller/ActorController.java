package com.fpmislata.movies.controller;

import com.fpmislata.movies.controller.model.actor.ActorCreateWeb;
import com.fpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.fpmislata.movies.controller.model.actor.ActorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody ActorCreateWeb actorCreateWeb){
        int id = actorService.create(ActorMapper.mapper.toActor(actorCreateWeb));
        ActorDetailWeb directorDetailWeb = new ActorDetailWeb(
                id,
                actorCreateWeb.getName(),
                actorCreateWeb.getBirthYear(),
                actorCreateWeb.getDeathYear()
        );
        return Response.builder()
                .data(directorDetailWeb)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody ActorUpdateWeb actorUpdateWeb) {
        actorUpdateWeb.setId(id);
        actorService.update(ActorMapper.mapper.toActor(actorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        actorService.delete(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable int id){
        return Response.builder()
                .data(ActorMapper.mapper.toActorDetailWeb(actorService.find(id)))
                .build();
    }
}