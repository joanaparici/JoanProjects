package com.fpmislata.JoanAparici1evalExam.persistence.dao;

import com.fpmislata.JoanAparici1evalExam.db.DBUtil;
import com.fpmislata.JoanAparici1evalExam.mapper.AuthorMapper;
import com.fpmislata.JoanAparici1evalExam.persistence.model.AuthorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDAO {
    public List<AuthorEntity> findByIsbn(Connection connection, String isbn) {
        List<AuthorEntity> authorEntities = new ArrayList<>();
        final String SQL = "SELECT * FROM authors WHERE id IN (SELECT author_id FROM books_authors WHERE book_id = (SELECT id FROM books WHERE isbn = ?))";
        try {
            List<Object> params = List.of(isbn);
            var resultSet = DBUtil.select(connection, SQL, params);
            while (resultSet.next()) {
                authorEntities.add(AuthorMapper.mapper.toAuthorEntity(resultSet));
            }
            return authorEntities;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
