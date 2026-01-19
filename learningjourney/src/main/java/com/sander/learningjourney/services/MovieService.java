package com.sander.learningjourney.services;

import com.sander.learningjourney.models.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Value("${omdb.api.endpoint}")
    private String endpoint;

    @Value("${omdb.api.key}")
    private String key;

    @Autowired
    private RestService restService;

    public Movie getMovie(String name) {
        String url = endpoint + "?apiKey=" + key + "&t=" + name;
        return this.restService.get(url, Movie.class);
    }
}
