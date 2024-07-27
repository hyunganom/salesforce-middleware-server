// adapters/external/HttpOutboundService.java
package com.hyunganom.salesforcemiddleware.adapters.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SalesforceLoginResult;
import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;
import com.hyunganom.salesforcemiddleware.core.ports.out.SalesforceOutboundPort;
import com.hyunganom.salesforcemiddleware.core.ports.out.SalesforceAuthenticationPort;
import com.hyunganom.salesforcemiddleware.utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class SalesforceOutboundPortImpl implements SalesforceOutboundPort {
    public static final String QUERY_PATH = "/services/data/v52.0/sobjects/chatGPT__e/";

    private final CloseableHttpClient closeableHttpClient;
    private final ObjectMapper objectMapper;
    private final SalesforceAuthenticationPort salesforceAuthenticationPort;

    @Override
    public void sendChat(SendToSalesforce sendChat) throws Exception {
        log.debug("sendChat(newChat={})", sendChat);

        SalesforceLoginResult salesforceLoginResult = salesforceAuthenticationPort.loginToSalesforce();
        log.debug("salesforceLoginResult={}", salesforceLoginResult);
        URIBuilder builder = new URIBuilder(salesforceLoginResult.getInstanceUrl());
        builder.setPath(QUERY_PATH);

        HttpPost post = new HttpPost(builder.build());
        post.setHeader("Authorization", "Bearer " + salesforceLoginResult.getAccessToken());
        post.setHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        post.setEntity(new StringEntity(objectMapper.writeValueAsString(sendChat), "UTF-8"));
        log.debug("post={}", post);

        HttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(post);
            log.debug("HTTP response received: {}", httpResponse.getStatusLine());

            HttpUtils.checkResponse(httpResponse);
            log.debug("Chat sent successfully!");
        } catch (IOException e) {
            log.error("IOException occurred during HTTP request: ", e);
            throw new Exception("Failed to send chat due to IO error", e);
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
            throw e;
        } finally {
            if (httpResponse != null) {
                EntityUtils.consumeQuietly(httpResponse.getEntity());
            }
        }
    }
}