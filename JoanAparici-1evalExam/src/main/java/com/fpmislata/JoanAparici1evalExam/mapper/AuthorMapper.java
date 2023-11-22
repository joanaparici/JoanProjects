package com.fpmislata.JoanAparici1evalExam.mapper;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Author;
import com.fpmislata.JoanAparici1evalExam.persistence.model.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorMapper mapper = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    AuthorEntity toAuthorEntity(ResultSet resultSet) throws SQLException;

    Author toAuthor(AuthorEntity authorEntity);
}
