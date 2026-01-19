package com.sander.learningjourney.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
class RestService {

    private RestClient restClient;

    private void createRestClient() {
        restClient = RestClient.create();
    }

    public <T> T get(String url, Class<T> responseType) {
        if (restClient == null) {
            createRestClient();
        }
        
        return restClient.get()
                .uri(url)
                .retrieve()
                .body(responseType);
    }
}
