package com.fpmislata.JoanAparici1evalExam.mapper;

import com.fpmislata.JoanAparici1evalExam.domain.entity.Publisher;
import com.fpmislata.JoanAparici1evalExam.persistence.model.PublisherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper mapper = Mappers.getMapper(PublisherMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    PublisherEntity toPublisherEntity(ResultSet resultSet) throws SQLException;

    Publisher toPublisher(PublisherEntity publisherEntity);

}
