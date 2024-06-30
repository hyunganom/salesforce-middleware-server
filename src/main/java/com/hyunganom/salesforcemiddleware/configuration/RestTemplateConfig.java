package com.hyunganom.salesforcemiddleware.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, Environment environment) {
        String token = environment.getProperty("openai.api.key");

        return builder
                .additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
                    request.getHeaders().set("Authorization", "Bearer " + token);
                    request.getHeaders().set("Content-Type", "application/json");
                    return execution.execute(request, body);
                })
                .build();
    }
}