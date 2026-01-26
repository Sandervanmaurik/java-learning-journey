package com.sander.learningjourney.controllers;

import com.sander.learningjourney.exceptions.NotFoundException;
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
    public ResponseEntity<Movie> getMovie(@PathVariable("name") String movieName) throws NotFoundException {
        try {
            return ResponseEntity.ok(movieService.getMovie(movieName));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
