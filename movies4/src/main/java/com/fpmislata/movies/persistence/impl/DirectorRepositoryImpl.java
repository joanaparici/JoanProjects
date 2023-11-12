package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public int insert(Director director) {
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        Connection connection = DBUtil.open(true);
        int id = DBUtil.insert(connection, SQL, params);
        //DBUtil.close(connection);
        return id;
    }

    @Override
    public Director find(int id) {
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open(true)){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if (resultSet.next()) {
                        return new Director(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("birthYear"),
                                (resultSet.getObject("deathYear") != null)? resultSet.getInt("deathYear") : null
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public Director findDirectorByMovieId(int id) {
        final String SQL = "SELECT * from directors join movies on directors.id=movies.director_id where movies.id=?";
        try (Connection connection = DBUtil.open(true)) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            // DBUtil.closeConnection(connection);
            if (resultSet.next()) {
                return new Director(
                        resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("birthYear"),
                resultSet.getInt("deathYear"));
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public void delete(int id) {
        final String SQL = "DELETE FROM directors WHERE id = ?";
        Connection connection = DBUtil.open(true);
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
        Connection connection = DBUtil.open(true);
        DBUtil.update(connection, SQL, params);
        DBUtil.close(connection);
    }
}

