package com.sander.learningjourney.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sander.learningjourney.exceptions.NotFoundException;
import com.sander.learningjourney.models.Movie;
import com.sander.learningjourney.models.Rating;

import tools.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    private static final String ENDPOINT = "http://example.com";
    private static final String KEY = "test-key";

    @Mock
    private RestService restService;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(ENDPOINT, KEY, restService, new ObjectMapper());
    }

    @Test
    void getMovie_parsesResponse() throws Exception {
        String rawJson = """
                {"Title":"Inception","Year":"2010","Rated":"PG-13","Released":"16 Jul 2010","Runtime":"148 min","Genre":"Action, Adventure, Sci-Fi","Director":"Christopher Nolan","Writer":"Christopher Nolan","Actors":"Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page","Plot":"A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO, but his tragic past may doom the project and his team to disaster.","Language":"English, Japanese, French","Country":"United States, United Kingdom","Awards":"Won 4 Oscars. 159 wins & 220 nominations total","Poster":"https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"8.8/10"},{"Source":"Rotten Tomatoes","Value":"87%"},{"Source":"Metacritic","Value":"74/100"}],"Metascore":"74","imdbRating":"8.8","imdbVotes":"2,767,518","imdbID":"tt1375666","Type":"movie","DVD":"N/A","BoxOffice":"$292,587,330","Production":"N/A","Website":"N/A","Response":"True"}
                """;

        String movieName = "Inception";
        String url = ENDPOINT + "?apiKey=" + KEY + "&t=" + movieName;
        when(restService.get(url)).thenReturn(Optional.of(rawJson));

        Movie movie = movieService.getMovie(movieName);

        Movie expected = new Movie();
        expected.setTitle("Inception");
        expected.setYear("2010");
        expected.setRated("PG-13");
        expected.setReleased("16 Jul 2010");
        expected.setRuntime("148 min");
        expected.setGenre("Action, Adventure, Sci-Fi");
        expected.setDirector("Christopher Nolan");
        expected.setWriter("Christopher Nolan");
        expected.setActors("Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page");
        expected.setPlot("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO, but his tragic past may doom the project and his team to disaster.");
        expected.setLanguage("English, Japanese, French");
        expected.setCountry("United States, United Kingdom");
        expected.setAwards("Won 4 Oscars. 159 wins & 220 nominations total");
        expected.setPoster("https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg");
        expected.setMetascore("74");
        expected.setImdbRating("8.8");
        expected.setImdbVotes("2,767,518");
        expected.setImdbID("tt1375666");
        expected.setType("movie");
        expected.setDvd("N/A");
        expected.setBoxOffice("$292,587,330");
        expected.setProduction("N/A");
        expected.setWebsite("N/A");
        expected.setResponse("True");

        Rating imdb = new Rating();
        imdb.setSource("Internet Movie Database");
        imdb.setValue("8.8/10");
        Rating rottenTomatoes = new Rating();
        rottenTomatoes.setSource("Rotten Tomatoes");
        rottenTomatoes.setValue("87%");
        Rating metacritic = new Rating();
        metacritic.setSource("Metacritic");
        metacritic.setValue("74/100");
        expected.setRatings(List.of(imdb, rottenTomatoes, metacritic));

        assertThat(movie).isEqualTo(expected);
    }

    @Test
    void getMovie_UnparsableResponse_throwsNotFoundException() {
        String rawJson = "{'invalid':'json'}";

        String movieName = "Inception";
        String url = ENDPOINT + "?apiKey=" + KEY + "&t=" + movieName;
        when(restService.get(url)).thenReturn(Optional.of(rawJson));

        try {
            movieService.getMovie(movieName);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(NotFoundException.class)
                         .hasMessage("Failed to parse movie response");
        }
    }
}
