package com.example.demo.config;

import lombok.Data;

@Data
public class ApiClientProperties {
    private long connectTimeout = 5000;
    private long readTimeout = 5000;
    private String baseUrl;
    private String apiKey;
    private int maxRetries = 3;
    private long retryDelay = 1000;
}
