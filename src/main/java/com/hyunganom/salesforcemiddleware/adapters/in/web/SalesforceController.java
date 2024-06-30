package com.hyunganom.salesforcemiddleware.adapters.in.web;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;
import com.hyunganom.salesforcemiddleware.core.ports.in.SalesforceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SalesforceController {

    private static final String SUCCESS_MESSAGE = "세일즈포스 보내기 성공!";
    private static final String FAILURE_MESSAGE = "세일즈포스 보내기 실패: ";

    private final SalesforceUseCase salesforceUseCase;

    @PostMapping("/send-chat")
    public ResponseEntity<String> sendChat(@RequestBody SendToSalesforce newChat) {
        try {
            salesforceUseCase.sendToSalesforce(newChat);
            return ResponseEntity.status(HttpStatus.CREATED).body(SUCCESS_MESSAGE);
        } catch (Exception e) {
            log.error("Failed to send to Salesforce: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAILURE_MESSAGE + e.getMessage());
        }
    }

    @PostMapping("/receive-chat")
    public ResponseEntity<String> handleContactWebhook(@RequestBody String receiveChat) {
        try {
            String response = salesforceUseCase.handleSalesforceWebhook(receiveChat);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error handling webhook: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAILURE_MESSAGE + e.getMessage());
        }
    }
}