package com.sander.learningjourney.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sander.learningjourney.security.SecurityConfig;
import com.sander.learningjourney.services.MovieService;

@WebMvcTest(MovieController.class)
@Import(SecurityConfig.class)
class MovieControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Test
    @WithMockUser("test")
    void getMovie_whenNotAuthenticated_returnsUnauthorized() throws Exception {
        mockMvc.perform(get("/movies/inception"))
                .andExpect(status().isUnauthorized());
    }
}
