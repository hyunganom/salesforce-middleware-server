package com.hyunganom.salesforcemiddleware.core.service;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;
import com.hyunganom.salesforcemiddleware.core.ports.in.ReceiveMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * 메시지를 수신하고 처리하는 서비스 클래스.
 * ChatGPT 및 Salesforce와의 상호 작용을 처리.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveMessageService implements ReceiveMessageUseCase {

    private final ChatGPTService chatGPTService;
    private final SalesforceService salesforceService;
    private final RabbitTemplate rabbitTemplate;

    /**
     * 일반 메시지를 수신하고 처리.
     *
     * @param message 수신된 메시지
     */
    @Override
    public void receiveMessage(String message) {
        try {
            String response = processMessage(message);
            sendMessageToQueue(response);
        } catch (Exception e) {
            log.error("Error processing message: {}", message, e);
        }
    }

    /**
     * ChatGPT 메시지를 수신하고 Salesforce로 전송.
     *
     * @param message 수신된 ChatGPT 메시지
     */
    @Override
    public void receiveGPTMessage(String message) {
        try {
            sendChatToSalesforce(message);
        } catch (Exception e) {
            log.error("Error sending message to Salesforce: {}", message, e);
        }
    }

    /**
     * 메시지를 ChatGPT 서비스로 처리하고 응답을 반환.
     *
     * @param message 수신된 메시지
     * @return ChatGPT 서비스의 응답
     */
    private String processMessage(String message) {
        String response = chatGPTService.chat(message);
        log.info("Response from ChatGPTService: {}", response);
        return response;
    }

    /**
     * 메시지를 RabbitMQ 큐에 전송.
     *
     * @param message 전송할 메시지
     */
    private void sendMessageToQueue(String message) {
        rabbitTemplate.convertAndSend("gptqueue", message);
        log.info("Sent message to gptqueue: {}", message);
    }

    /**
     * 메시지를 Salesforce로 전송.
     *
     * @param message 전송할 메시지
     * @throws Exception 전송 중 발생할 수 있는 예외
     */
    private void sendChatToSalesforce(String message) throws Exception {
        SendToSalesforce newChat = new SendToSalesforce();
        newChat.setReceive(message);
        salesforceService.sendToSalesforce(newChat);
        log.info("Sent message to Salesforce: {}", message);
    }
}