package com.sander.learningjourney.models;

import lombok.Data;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

@Data
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class Movie {
    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private List<Rating> ratings;
    private String metascore;

    @JsonAlias("imdbRating")
    private String imdbRating;

    @JsonAlias("imdbVotes")
    private String imdbVotes;

    @JsonAlias("imdbID")
    private String imdbID;

    @JsonAlias("DVD")
    private String dvd;

    private String type;
    private String boxOffice;
    private String production;
    private String website;
    private String response;
}
