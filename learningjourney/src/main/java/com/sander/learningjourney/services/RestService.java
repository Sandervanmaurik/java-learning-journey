package com.sander.learningjourney.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RestService {
    private RestClient restClient;

    private void createRestClient() {
        restClient = RestClient.create();
    }

    public Optional<String> get(String url) {
        if (restClient == null) {
            createRestClient();
        }

        String body = restClient.get()
                .uri(url)
                .retrieve()
                .body(String.class);
        return Optional.ofNullable(body);
    }
}
