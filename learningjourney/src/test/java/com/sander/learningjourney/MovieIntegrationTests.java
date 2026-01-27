package com.sander.learningjourney;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class MovieIntegrationTests {

    private RestTestClient restTestClient;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        restTestClient = RestTestClient.bindToApplicationContext(context).build();
    }

    @Test
    @WithUserDetails("admin")
    void getMovie() {
        restTestClient.get()
                .uri("/movies/{name}", "inception")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @WithUserDetails("admin")
    void getNonExistentMovie() {
        restTestClient.get()
                .uri("/movies/{name}", "non-existent-movie")
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}
