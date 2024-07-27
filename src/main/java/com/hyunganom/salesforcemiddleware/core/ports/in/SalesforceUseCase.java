package com.hyunganom.salesforcemiddleware.core.ports.in;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;

/**
 * Salesforce와 상호 작용하는 Use Case 인터페이스.
 * 데이터를 Salesforce로 전송하고 웹훅 요청을 처리.
 */
public interface SalesforceUseCase {
    /**
     * Salesforce로 데이터를 전송.
     *
     * @param newChat Salesforce로 전송할 데이터 객체
     */
    void sendToSalesforce(SendToSalesforce newChat);

    /**
     * Salesforce 웹훅 요청을 처리.
     *
     * @param receiveChat 수신된 웹훅 데이터
     * @return 처리된 결과
     */
    String handleSalesforceWebhook(String receiveChat);
}