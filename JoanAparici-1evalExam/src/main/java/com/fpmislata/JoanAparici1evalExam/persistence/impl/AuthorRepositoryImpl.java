package com.fpmislata.JoanAparici1evalExam.persistence.impl;

import com.fpmislata.JoanAparici1evalExam.db.DBUtil;
import com.fpmislata.JoanAparici1evalExam.domain.entity.Author;
import com.fpmislata.JoanAparici1evalExam.domain.repository.AuthorRepository;
import com.fpmislata.JoanAparici1evalExam.mapper.AuthorMapper;
import com.fpmislata.JoanAparici1evalExam.persistence.dao.AuthorDAO;
import com.fpmislata.JoanAparici1evalExam.persistence.model.AuthorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @Autowired
    AuthorDAO authorDAO;
    @Override
    public Optional<Author> find(int id) {
        return Optional.empty();
    }

    @Override
    public List<Author> findByIsbn(String isbn) {
        try(Connection connection= DBUtil.open(true)) {
            List<AuthorEntity> authorEntities = authorDAO.findByIsbn(connection, isbn);
            return authorEntities.stream()
                    .map(authorEntity -> AuthorMapper.mapper.toAuthor(authorEntity))
                    .toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
