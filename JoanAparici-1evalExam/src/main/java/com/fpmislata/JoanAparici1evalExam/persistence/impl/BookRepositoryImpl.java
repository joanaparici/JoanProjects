package com.fpmislata.JoanAparici1evalExam.persistence.impl;

import com.fpmislata.JoanAparici1evalExam.db.DBUtil;
import com.fpmislata.JoanAparici1evalExam.domain.entity.Book;
import com.fpmislata.JoanAparici1evalExam.domain.repository.BookRepository;
import com.fpmislata.JoanAparici1evalExam.mapper.BookMapper;
import com.fpmislata.JoanAparici1evalExam.persistence.dao.BookDAO;
import com.fpmislata.JoanAparici1evalExam.persistence.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    BookDAO bookDAO;

    @Override
    public List<Book> getAll() {
        try(Connection connection = DBUtil.open(true)) {
            List<BookEntity> bookEntities = bookDAO.findAll(connection);
            List<Book> books = bookEntities.stream()
                    .map(bookEntity -> BookMapper.mapper.toBook(bookEntity))
                    .toList();
            return books;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        try(Connection connection = DBUtil.open(true)) {
            Optional<BookEntity> bookEntity = bookDAO.findByIsbn(connection, isbn);
            return bookEntity.map(BookMapper.mapper::toBook);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
