package com.hyunganom.salesforcemiddleware.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunganom.salesforcemiddleware.adapters.out.SalesforceOutboundPortImpl;
import com.hyunganom.salesforcemiddleware.adapters.out.SalesforceAuthenticationAdapter;
import com.hyunganom.salesforcemiddleware.core.ports.out.SalesforceOutboundPort;
import com.hyunganom.salesforcemiddleware.core.ports.out.SalesforceAuthenticationPort;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfiguration {

    @Bean
    @Primary
    public SalesforceAuthenticationPort salesforcePort(CloseableHttpClient closeableHttpClient, SalesforceConfigurationProperties salesforceConfigurationProperties, ObjectMapper objectMapper) {
        return new SalesforceAuthenticationAdapter(closeableHttpClient, salesforceConfigurationProperties, objectMapper);
    }

    @Bean
    public SalesforceOutboundPort salesforceOutboundPort(CloseableHttpClient closeableHttpClient, ObjectMapper objectMapper, SalesforceAuthenticationPort salesforceAuthenticationPort) {
        return new SalesforceOutboundPortImpl(closeableHttpClient, objectMapper, salesforceAuthenticationPort);
    }

}



