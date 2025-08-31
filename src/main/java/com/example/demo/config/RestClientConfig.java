package com.example.demo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import java.time.Duration;

@Configuration
@EnableRetry
public class RestClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "api.client")
    public ApiClientProperties apiClientProperties() {
        return new ApiClientProperties();
    }

    @Bean
    public RestTemplate apiRestTemplate(RestTemplateBuilder builder, ApiClientProperties properties) {
        return builder
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeout()))
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeout()))
                .additionalInterceptors(clientHttpRequestInterceptor())
                .build();
    }

    private ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (request, body, execution) -> {
            request.getHeaders().add("User-Agent", "Demo-App/1.0");
            return execution.execute(request, body);
        };
    }
}
