package com.fpmislata.JoanAparici1evalExam.persistence.impl;

import com.fpmislata.JoanAparici1evalExam.db.DBUtil;
import com.fpmislata.JoanAparici1evalExam.domain.entity.Publisher;
import com.fpmislata.JoanAparici1evalExam.domain.repository.PublisherRepository;
import com.fpmislata.JoanAparici1evalExam.mapper.PublisherMapper;
import com.fpmislata.JoanAparici1evalExam.persistence.dao.PublisherDAO;
import com.fpmislata.JoanAparici1evalExam.persistence.model.PublisherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

    @Autowired
    PublisherDAO publisherDAO;
    @Override
    public Optional<Publisher> find(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Publisher> findPublisherByIsbn(String isbn) {
        try (Connection connection = DBUtil.open(true)) {
            Optional<PublisherEntity> publisherEntity = publisherDAO.findByPublisherIsbn(connection, isbn);
            if (publisherEntity.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(PublisherMapper.mapper.toPublisher(publisherEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
