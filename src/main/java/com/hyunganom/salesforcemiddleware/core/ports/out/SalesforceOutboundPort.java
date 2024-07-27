package com.hyunganom.salesforcemiddleware.core.ports.out;

import com.hyunganom.salesforcemiddleware.core.domain.salesforce.SendToSalesforce;

/**
 * Salesforce로 데이터를 전송하기 위한 포트 인터페이스.
 * 채팅 데이터를 Salesforce로 전송.
 */
public interface SalesforceOutboundPort {
    /**
     * 채팅 데이터를 Salesforce로 전송.
     *
     * @param sendChat Salesforce로 전송할 채팅 데이터 객체
     * @throws Exception 전송 중 발생할 수 있는 예외
     */
    void sendChat(SendToSalesforce sendChat) throws Exception;
}