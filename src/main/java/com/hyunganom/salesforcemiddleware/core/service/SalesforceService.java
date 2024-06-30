// core/service/OutboundService.java
package com.hyunganom.salesforcemiddleware.core.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;
import com.hyunganom.salesforcemiddleware.core.ports.in.SalesforceUseCase;
import com.hyunganom.salesforcemiddleware.core.ports.out.SalesforceOutboundPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesforceService implements SalesforceUseCase {

    private static final String SEND_FIELD = "send__c";

    private final @Qualifier("salesforceOutboundPortImpl") SalesforceOutboundPort salesforceOutboundPort;
    private final ChatGPTService chatGPTService;
    private final ObjectMapper objectMapper;

    @Override
    public void sendToSalesforce(SendToSalesforce newChat) {
        try {
            salesforceOutboundPort.sendChat(newChat);
        } catch (Exception e) {
            log.error("Failed to send to Salesforce: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to send to Salesforce", e);
        }
    }

    @Override
    public String handleSalesforceWebhook(String receiveChat) {
        try {
            JsonNode jsonNode = objectMapper.readTree(receiveChat);
            String sendValue = jsonNode.get(SEND_FIELD).asText();

            log.debug("Received from Salesforce: {}", receiveChat);
            log.debug("Salesforce send__c value: {}", sendValue);

            String response = chatGPTService.chat(sendValue);

            log.debug("ChatGPT response: {}", response);

            SendToSalesforce newChat = new SendToSalesforce();
            newChat.setReceive(response);

            sendToSalesforce(newChat);

            return response;
        } catch (Exception e) {
            log.error("Error handling webhook: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to handle Salesforce webhook", e);
        }
    }
}