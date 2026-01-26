package com.sander.learningjourney.models;

import java.util.List;

import lombok.Builder;

@Builder
public record MovieSummary(
        String title,
        String year,
        String rated,
        String released,
        String runtime,
        String genre,
        String director,
        String writer,
        String actors,
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
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .writer(movie.getWriter())
                .actors(movie.getActors())
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
}
