package com.sander.learningjourney.services;

import com.sander.learningjourney.models.Movie;
import com.sander.learningjourney.models.MovieSummary;
import com.sander.learningjourney.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class MovieService {

    private final String endpoint;
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

    public MovieSummary getMovie(String name) throws NotFoundException {
        String url = endpoint + "?apiKey=" + key + "&t=" + name;
        final var response = this.restService.get(url)
                .orElseThrow(() -> new NotFoundException("Movie not found: " + name, null));

        try {
            final Movie movieResponse = objectMapper.readValue(response, Movie.class);
            if (movieResponse.isFailure()) {
                log.warn("Movie not found: {}", name);
                throw new NotFoundException("Movie not found: " + name, null);
            }
            return MovieSummary.from(movieResponse);
        } catch (StreamReadException e) {
            log.warn("Movie response can not be parsed");
            throw new NotFoundException("Failed to parse movie response", e);
        }
    }
}
