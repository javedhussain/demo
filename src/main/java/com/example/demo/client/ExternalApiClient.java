package com.example.demo.client;

import com.example.demo.config.ApiClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExternalApiClient {
    
    private final RestTemplate apiRestTemplate;
    private final ApiClientProperties properties;

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 1000))
    public ResponseEntity<String> fetchData(String endpoint, Object request) {
        String url = UriComponentsBuilder
            .fromHttpUrl(properties.getBaseUrl())
            .path(endpoint)
            .build()
            .toUriString();

        log.debug("Making API request to: {}", url);
        
        try {
            ResponseEntity<String> response = apiRestTemplate.postForEntity(url, request, String.class);
            log.debug("Received response: {}", response.getStatusCode());
            return response;
        } catch (Exception e) {
            log.error("Error calling external API: {}", e.getMessage(), e);
            throw e;
        }
    }
}
