package com.sander.learningjourney.controllers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sander.learningjourney.config.SpringSecurityWebTestConfig;
import com.sander.learningjourney.services.MovieService;

@SpringBootTest(classes = SpringSecurityWebTestConfig.class)
@Import(SpringSecurityWebTestConfig.class)
@AutoConfigureMockMvc
class MovieControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MovieService movieService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    void getMovie_whenNotAuthenticated_returnsUnauthorized() throws Exception {
        mockMvc.perform(get("/movies/inception"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("admin")
    void getMovie_whenAdminAuthenticated_returnsOk() throws Exception {
        mockMvc.perform(get("/movies/inception").with(testSecurityContext()))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user")
    void getMovie_whenUserAuthenticated_returnsUnauthorized() throws Exception {
        mockMvc.perform(get("/movies/inception").with(testSecurityContext()))
                .andExpect(status().isForbidden());
    }

    @Test
    void loadUser_whenUnknownUser_throwsUsernameNotFoundException() {
        assertThatThrownBy(() -> userDetailsService.loadUserByUsername("unknown_user"))
                .isInstanceOf(UsernameNotFoundException.class);
    }
}
