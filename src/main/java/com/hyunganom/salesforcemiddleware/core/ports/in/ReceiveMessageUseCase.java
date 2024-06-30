package com.hyunganom.salesforcemiddleware.core.ports.in;

public interface ReceiveMessageUseCase {
    void receiveMessage(String message);
    void receiveGPTMessage(String message);
}
