package com.hyunganom.salesforcemiddleware.adapters.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunganom.salesforcemiddleware.configuration.SalesforceConfigurationProperties;
import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SalesforceLoginResult;
import com.hyunganom.salesforcemiddleware.core.ports.out.SalesforceAuthenticationPort;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SalesforceAuthenticationAdapter implements SalesforceAuthenticationPort {

    private static final String TOKEN_URL = "https://login.salesforce.com/services/oauth2/token";

    private final CloseableHttpClient closeableHttpClient;
    private final SalesforceConfigurationProperties salesforceConfigurationProperties;
    private final ObjectMapper objectMapper;

    @Autowired
    public SalesforceAuthenticationAdapter(CloseableHttpClient closeableHttpClient, SalesforceConfigurationProperties salesforceConfigurationProperties, ObjectMapper objectMapper) {
        this.closeableHttpClient = closeableHttpClient;
        this.salesforceConfigurationProperties = salesforceConfigurationProperties;
        this.objectMapper = objectMapper;
    }

    @Override
    public SalesforceLoginResult loginToSalesforce() throws Exception {
        List<NameValuePair> loginParams = new ArrayList<>();
        loginParams.add(new BasicNameValuePair("client_id", salesforceConfigurationProperties.getConsumerKey()));
        loginParams.add(new BasicNameValuePair("client_secret", salesforceConfigurationProperties.getConsumerSecret()));
        loginParams.add(new BasicNameValuePair("grant_type", "password"));
        loginParams.add(new BasicNameValuePair("username", salesforceConfigurationProperties.getUsername()));
        loginParams.add(new BasicNameValuePair("password", salesforceConfigurationProperties.getPassword()));

        HttpPost post = new HttpPost(TOKEN_URL);
        post.setEntity(new UrlEncodedFormEntity(loginParams));

        HttpResponse httpResponse = closeableHttpClient.execute(post);
        log.debug("HTTP response status: {}", httpResponse.getStatusLine());

        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            throw new IOException("Failed to login to Salesforce: " + httpResponse.getStatusLine().getReasonPhrase());
        }

        SalesforceLoginResult salesforceLoginResult = objectMapper.readValue(httpResponse.getEntity().getContent(), SalesforceLoginResult.class);
        log.debug("salesforceLoginResult={}", salesforceLoginResult);
        return salesforceLoginResult;
    }
}