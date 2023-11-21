package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Autowired
    MovieDAO movieDAO;

    @Override
    public List<Movie> getAll(Integer page, Integer pageSize) {
        try(Connection connection = DBUtil.open(true)) {
            List<MovieEntity> movieEntities = movieDAO.findAll(connection, page, pageSize);
            List<Movie> movies = movieEntities.stream()
                    .map(movieEntity -> MovieMapper.mapper.toMovie(movieEntity))
                    .toList();
            return movies;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> find(int id) {
        try (Connection connection = DBUtil.open(true)){
            Optional<MovieEntity> movieEntity = movieDAO.find(connection, id);
            return movieEntity.map(MovieMapper.mapper::toMovie);
//            SUSTITUYE A ESTO
//                       if(movieEntity.isEmpty()) {
//                return Optional.empty();
//            }
//            return Optional.of(MovieMapper.mapper.toMovie(movieEntity.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)) {
            return movieDAO.getTotalNumberOfRecords(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Movie movie) {
        try (Connection connection = DBUtil.open(true)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            return movieDAO.insert(connection, movieEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {
        try (Connection connection = DBUtil.open(true)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            movieDAO.update(connection, movieEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void delete(int id) {
        try (Connection connection = DBUtil.open(true)){
            movieDAO.delete(connection, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Movie> findByTitle(String title) {
        try (Connection connection = DBUtil.open(true)){
            Optional<MovieEntity> movieEntity = movieDAO.findByTitle(connection, title);
            return movieEntity.map(MovieMapper.mapper::toMovie);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}