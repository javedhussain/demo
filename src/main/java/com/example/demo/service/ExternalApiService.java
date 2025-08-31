package com.example.demo.service;

import com.example.demo.client.ExternalApiClient;
import com.example.demo.exception.ExternalApiException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalApiService {
    
    private final ExternalApiClient apiClient;
    
    public String processExternalRequest(Object request) {
        try {
            var response = apiClient.fetchData("/api/endpoint", request);
            return response.getBody();
        } catch (Exception e) {
            log.error("Failed to process external request", e);
            throw new ExternalApiException("Failed to process external request", e);
        }
    }
}
