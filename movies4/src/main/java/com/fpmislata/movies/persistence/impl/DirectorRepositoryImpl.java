package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.persistence.DirectorRepository;
import com.fpmislata.movies.exception.SQLStatmentException;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public int insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        Connection connection = DBUtil.open();
        int id = DBUtil.insert(connection, SQL, params);
        //DBUtil.close(connection);
        return id;
    }

    @Override
    public Optional<Director> find(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                return Optional.of(
                        new Director(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                resultSet.getInt("deathYear")
                        )
                );
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public void delete(int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        Connection connection = DBUtil.open();
        DBUtil.delete(connection, SQL, List.of(id));
        DBUtil.close(connection);
    }

    @Override
    public void update(Director director) {
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        params.add(director.getId());
        Connection connection = DBUtil.open();
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }
}

