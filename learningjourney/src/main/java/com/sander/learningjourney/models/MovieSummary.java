package com.sander.learningjourney.models;

import java.util.Arrays;
import java.util.List;

import lombok.Builder;

@Builder
public record MovieSummary(
        String title,
        String year,
        String rated,
        String released,
        String runtime,
        List<String> genre,
        String director,
        String writer,
        List<String> actors,
        String plot,
        String language,
        String country,
        String awards,
        String poster,
        List<Rating> ratings,
        String metascore,
        String imdbRating,
        String imdbVotes,
        String imdbID,
        String type,
        String dvd,
        String boxOffice,
        String production,
        String website) {

    public static MovieSummary from(Movie movie) {
        if (movie == null) {
            return null;
        }
        return MovieSummary.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .rated(movie.getRated())
                .released(movie.getReleased())
                .runtime(movie.getRuntime())
                .genre(splitCommaSeparated(movie.getGenre()))
                .director(movie.getDirector())
                .writer(movie.getWriter())
                .actors(splitCommaSeparated(movie.getActors()))
                .plot(movie.getPlot())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .awards(movie.getAwards())
                .poster(movie.getPoster())
                .ratings(movie.getRatings() == null ? null : List.copyOf(movie.getRatings()))
                .metascore(movie.getMetascore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .imdbID(movie.getImdbID())
                .type(movie.getType())
                .dvd(movie.getDvd())
                .boxOffice(movie.getBoxOffice())
                .production(movie.getProduction())
                .website(movie.getWebsite())
                .build();
    }

    private static List<String> splitCommaSeparated(String value) {
        if (value == null || value.isBlank()) {
            return List.of();
        }
        return Arrays.stream(value.split(","))
                .map(String::trim)
                .filter(item -> !item.isBlank())
                .toList();
    }
}
