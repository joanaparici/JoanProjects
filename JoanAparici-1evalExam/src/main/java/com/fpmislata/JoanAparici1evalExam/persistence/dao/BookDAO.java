package com.fpmislata.JoanAparici1evalExam.persistence.dao;

import com.fpmislata.JoanAparici1evalExam.db.DBUtil;
import com.fpmislata.JoanAparici1evalExam.mapper.BookMapper;
import com.fpmislata.JoanAparici1evalExam.persistence.model.BookEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    public List<BookEntity> findAll(Connection connection) {
        List<Object> params = List.of();
        String sql = "SELECT * FROM books";

        List<BookEntity> bookEntities = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            while (resultSet.next()) {
                bookEntities.add(BookMapper.mapper.toBookEntity(resultSet));
            }
            return bookEntities;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Optional<BookEntity> findByIsbn(Connection connection, String isbn) {
        List<Object> params = List.of(isbn);
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try {
            ResultSet resultSet = DBUtil.select(connection, sql, params);
            return Optional.ofNullable(resultSet.next() ? BookMapper.mapper.toBookEntity(resultSet) : null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}

