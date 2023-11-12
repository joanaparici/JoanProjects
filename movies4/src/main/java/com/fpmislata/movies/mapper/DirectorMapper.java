package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);


    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
}