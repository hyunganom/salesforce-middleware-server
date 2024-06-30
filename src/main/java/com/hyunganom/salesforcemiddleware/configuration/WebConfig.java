// configuration/WebConfig.java
package com.hyunganom.salesforcemiddleware.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hyunganom.salesforcemiddleware.interceptors.LoggingInterceptor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoggingInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public LoggingInterceptor getLoggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Bean
    public CloseableHttpClient closeableHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(30 * 1000) // 30초
                .setSocketTimeout(30 * 1000)  // 30초
                .build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
}