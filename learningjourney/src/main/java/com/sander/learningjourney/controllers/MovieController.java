package com.sander.learningjourney.controllers;

import com.sander.learningjourney.models.Movie;
import com.sander.learningjourney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("{name}")
    public ResponseEntity<Movie> getMovie(@PathVariable("name") String movieName) {
        var movie = movieService.getMovie(movieName);
        System.out.println(movie);
        return ResponseEntity.ok(movie);
    }
}
