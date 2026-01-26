package com.sander.learningjourney.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestService {
    private final RestClient restClient;

    public RestService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public Optional<String> get(String url) {
        String body = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
        return Optional.ofNullable(body);
    }
}
