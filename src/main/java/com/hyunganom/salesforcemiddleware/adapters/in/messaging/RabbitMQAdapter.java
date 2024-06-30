package com.hyunganom.salesforcemiddleware.adapters.in.messaging;

import com.hyunganom.salesforcemiddleware.core.ports.in.ReceiveMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitMQAdapter {

    private final ReceiveMessageUseCase receiveMessageUseCase;

    @RabbitListener(queues = "q.api")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        receiveMessageUseCase.receiveMessage(message);
    }

    @RabbitListener(queues = "gptqueue")
    public void receiveGPTMessage(String message) {
        log.info("Received message from gptqueue: {}", message);
        receiveMessageUseCase.receiveGPTMessage(message);
    }
}