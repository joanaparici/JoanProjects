package com.fpmislata.JoanAparici1evalExam.persistence.dao;

import com.fpmislata.JoanAparici1evalExam.db.DBUtil;
import com.fpmislata.JoanAparici1evalExam.mapper.PublisherMapper;
import com.fpmislata.JoanAparici1evalExam.persistence.model.PublisherEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PublisherDAO {
    public Optional<PublisherEntity> findByPublisherIsbn(Connection connection, String isbn) {
        final String sql = """
                SELECT p.* FROM publisher p
                INNER JOIN books b ON b.id_publisher = p.id
                WHERE b.isbn = ?
                LIMIT 1
                """;
        try{
            ResultSet resultSet = DBUtil.select(connection, sql, List.of(isbn));
            return Optional.ofNullable(resultSet.next()? PublisherMapper.mapper.toPublisherEntity(resultSet):null);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

//    public Optional<PublisherEntity> findByIsbn(Connection connection, String isbn) {
//        final String sql = """
//                SELECT p.* FROM publisher p
//                INNER JOIN books b ON b.id_publisher = p.id
//                WHERE b.isbn = ?
//                LIMIT 1
//                """;
//        try{
//            ResultSet resultSet = DBUtil.select(connection, sql, List.of(isbn));
//            return Optional.ofNullable(resultSet.next()? PublisherMapper.mapper.toPublisherEntity(resultSet):null);
//        } catch (SQLException e) {
//            throw new RuntimeException();
//        }
//    }
}
