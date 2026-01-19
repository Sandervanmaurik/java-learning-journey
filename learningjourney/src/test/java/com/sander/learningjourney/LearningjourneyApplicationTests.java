package com.sander.learningjourney;

import com.sander.learningjourney.controllers.MovieController;
import com.sander.learningjourney.models.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LearningjourneyApplicationTests {

    @Autowired
    private MovieController movieController;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetMovieEndpoint() {
        String movieName = "Inception";
        Movie response = movieController.getMovie(movieName).getBody();
        
        assertThat(response).isNotNull();
        assertThat(response.getTitle()).isNotEmpty();
    }

}
