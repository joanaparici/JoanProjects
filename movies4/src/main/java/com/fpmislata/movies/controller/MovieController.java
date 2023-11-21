package com.fpmislata.movies.controller;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.controller.model.movie.MovieUpdateWeb;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.http_response.Response;

import com.fpmislata.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(MovieController.MOVIES)
@RestController
public class MovieController {

    public static final String MOVIES = "/movies";

    @Autowired
    private MovieService movieService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    @Value("${application.url}")
    private String urlBase;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        List<Movie> movies = (page != null)? movieService.getAll(page, pageSize) : movieService.getAll();
        List<MovieListWeb> moviesWeb = movies.stream()
                .map(movie -> MovieMapper.mapper.toMovieListWeb(movie))
                .toList();
        int totalRecords = movieService.getTotalNumberOfRecords();
        Response response = Response.builder()
                .data(moviesWeb)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWebCreateWeb) {
        int id = movieService.create(
                MovieMapper.mapper.toMovie(movieCreateWebCreateWeb),
                movieCreateWebCreateWeb.getDirectorId(),
                movieCreateWebCreateWeb.getActorIds()
        );
        MovieListWeb movieListWeb = new MovieListWeb();
        movieListWeb.setTitle(movieCreateWebCreateWeb.getTitle());
        movieListWeb.setId(id);
        return Response.builder()
                .data(movieListWeb)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody MovieUpdateWeb movieUpdateWeb) {
        movieService.update(
                id,
                MovieMapper.mapper.toMovie(movieUpdateWeb),
                movieUpdateWeb.getDirectorId(),
                movieUpdateWeb.getActorIds()
        );
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable int id) {
        return Response.builder()
                .data(MovieMapper.mapper.toMovieDetailWeb(movieService.find(id)))
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search/{title}")
    public Response findByTitle(@PathVariable("title") String title) {
        return Response.builder()
                .data(MovieMapper.mapper.toMovieDetailWeb(movieService.findByTitle(title)))
                .build();
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        movieService.delete(id);
    }
}