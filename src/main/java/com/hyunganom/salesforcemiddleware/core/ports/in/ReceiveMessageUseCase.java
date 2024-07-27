package com.hyunganom.salesforcemiddleware.core.ports.in;

/**
 * 메시지를 수신하는 Use Case 인터페이스.
 * 일반 메시지와 ChatGPT 메시지를 수신.
 */
public interface ReceiveMessageUseCase {
    /**
     * 일반 메시지를 수신.
     *
     * @param message 수신된 메시지
     */
    void receiveMessage(String message);

    /**
     * ChatGPT 메시지를 수신.
     *
     * @param message 수신된 ChatGPT 메시지
     */
    void receiveGPTMessage(String message);
}