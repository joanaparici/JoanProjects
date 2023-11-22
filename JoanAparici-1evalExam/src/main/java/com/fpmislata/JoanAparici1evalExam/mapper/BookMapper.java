package com.fpmislata.JoanAparici1evalExam.mapper;

import com.fpmislata.JoanAparici1evalExam.controller.model.book.BookDetailWeb;
import com.fpmislata.JoanAparici1evalExam.controller.model.book.BookListWeb;
import com.fpmislata.JoanAparici1evalExam.domain.entity.Book;
import com.fpmislata.JoanAparici1evalExam.persistence.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper mapper = Mappers.getMapper(BookMapper.class);


    @Mapping(target = "isbn", expression = "java(resultSet.getString(\"isbn\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "synopsis", expression = "java(resultSet.getString(\"synopsis\"))")
//    @Mapping(target = "publisherId", expression = "java(resultSet.getInt(\"id_publisher\"))")
    BookEntity toBookEntity(ResultSet resultSet) throws SQLException;

    Book toBook(BookEntity bookEntity);

    BookListWeb toBookListWeb(Book book);

    BookDetailWeb toBookDetailWeb(Book book);
}


