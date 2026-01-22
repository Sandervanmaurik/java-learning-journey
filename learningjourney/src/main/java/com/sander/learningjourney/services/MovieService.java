package com.sander.learningjourney.services;

import com.sander.learningjourney.models.Movie;
import com.sander.learningjourney.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Service;

import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class MovieService {

    @Value("${omdb.api.endpoint}")
    private final String endpoint;

    @Value("${omdb.api.key}")
    private final String key;

    private final RestService restService;

    private final ObjectMapper objectMapper;

    public MovieService(
            @Value("${omdb.api.endpoint}") String endpoint,
            @Value("${omdb.api.key}") String key,
            RestService restService,
            ObjectMapper objectMapper) {
        this.endpoint = endpoint;
        this.key = key;
        this.restService = restService;
        this.objectMapper = objectMapper;
    }

    public Movie getMovie(String name) throws NotFoundException {
        String url = endpoint + "?apiKey=" + key + "&t=" + name;
        var response = this.restService.get(url)
                .orElseThrow(() -> new NotFoundException("Movie not found: " + name));
        System.out.println(response);

        try {
            Movie movieResponse = objectMapper.readValue(response, Movie.class);
            if (movieResponse != null && "False".equals(movieResponse.getResponse())) {
                log.warn("Movie not found: {}", name);
                throw new NotFoundException("Movie not found: " + name);
            }
            return movieResponse;
        } catch (JsonParseException e) {
            log.warn("Movie response can not be parsed");
            throw new IllegalStateException("Failed to parse movie response", e);
        }
    }
}
